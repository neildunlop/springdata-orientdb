package com.pathfinder.data.orient.object.repository.support;

import com.pathfinder.data.orient.commons.repository.SourceType;
import com.pathfinder.data.orient.commons.repository.annotation.Cluster;
import com.pathfinder.data.orient.commons.repository.annotation.Source;
import com.pathfinder.data.orient.commons.repository.query.OrientQueryLookupStrategy;
import com.pathfinder.data.orient.commons.repository.support.OrientMetamodelEntityInformation;
import com.pathfinder.data.orient.commons.repository.support.SimpleOrientRepository;
import com.pathfinder.data.orient.object.repository.OrientObjectRepository;
import org.springframework.core.annotation.AnnotationUtils;
import com.pathfinder.data.orient.object.OrientObjectOperations;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;


import java.io.Serializable;

//TODO: find out why inheriting from OrientRepositoryFactory does not work; would save some code; but this here works
public class OrientObjectRepositoryFactory extends RepositoryFactorySupport {

    private final OrientObjectOperations operations;

    public OrientObjectRepositoryFactory(OrientObjectOperations operations) {
        super();
        this.operations = operations;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return (EntityInformation<T, ID>) new OrientMetamodelEntityInformation<T>(domainClass);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Object getTargetRepository(RepositoryInformation metadata) {
        EntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
        Class<?> repositoryInterface = metadata.getRepositoryInterface();
        Class<?> javaType = entityInformation.getJavaType();
        String cluster = getCustomCluster(metadata);

        if (isObjectRepository(metadata.getRepositoryInterface())) {
            if (cluster != null) {
                return new SimpleOrientObjectRepository(operations, javaType, cluster, repositoryInterface);
            } else {
                return new SimpleOrientObjectRepository(operations, javaType, repositoryInterface);
            }
        } else {
            if (cluster != null) {
                return new SimpleOrientRepository(operations, javaType, cluster, repositoryInterface);
            } else {
                return new SimpleOrientRepository(operations, javaType, repositoryInterface);
            }
        }
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key, EvaluationContextProvider evaluationContextProvider) {
        return OrientQueryLookupStrategy.create(operations, key);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isObjectRepository(metadata.getRepositoryInterface())) {
            return SimpleOrientObjectRepository.class;
        } else {
            return SimpleOrientRepository.class;
        }
    }

    private boolean isObjectRepository(Class<?> repositoryInterface) {
        return OrientObjectRepository.class.isAssignableFrom(repositoryInterface);
    }

    /**
     * Get Custom Cluster Name.
     * Method looks for {@link Source} and {@link Cluster} annotation.
     * <p>
     * If {@link Source} is not null and {@link Source#type()} equals to
     * {@link SourceType#CLUSTER} then returns {@link Source#value()}
     * <p>
     * If {@link Cluster} is not null then returns {@link Cluster#value()}
     *
     * @param metadata
     * @return cluster name or null if it's not defined
     */
    private String getCustomCluster(RepositoryMetadata metadata) {
        Class<?> repositoryInterface = metadata.getRepositoryInterface();

        Source source = AnnotationUtils.getAnnotation(repositoryInterface, Source.class);
        if (source != null && SourceType.CLUSTER.equals(source.type())) {
            return source.value();
        }

        Cluster cluster = AnnotationUtils.getAnnotation(repositoryInterface, Cluster.class);
        if (cluster != null) {
            return cluster.value();
        }
        return null;
    }
}

