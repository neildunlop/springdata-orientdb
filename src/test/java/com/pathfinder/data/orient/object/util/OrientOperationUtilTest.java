package com.pathfinder.data.orient.object.util;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.pathfinder.data.orient.object.OrientDbObjectTestConfiguration;
import com.pathfinder.data.orient.object.OrientObjectDatabaseFactory;
import com.pathfinder.data.orient.object.OrientObjectOperations;
import com.pathfinder.data.orient.object.domain.Address;
import com.pathfinder.data.orient.object.domain.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = OrientDbObjectTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OrientOperationUtilTest {

    @Autowired
    OrientObjectOperations template;

    @Autowired
    OrientObjectDatabaseFactory factory;

    @Before
    public void before() {
        try (OObjectDatabaseTx db = factory.openDatabase()) {
            db.getEntityManager().registerEntityClass(Employee.class);
        }
    }

    @Test
    public void getRidTest() {
        Address address = new Address();
        Assert.assertNull(template.getRid(address));

        address.setId("123");
        Assert.assertEquals(template.getRid(address), "123");
    }

    @Test
    public void getRidFromParentTest() {
        Employee employee = new Employee();
        Assert.assertNull(template.getRid(employee));

        employee.setRid("123");
        Assert.assertEquals(template.getRid(employee), "123");
    }

    @Test
    public void getRidFromProxy() {
        Employee employee = new Employee();
        Employee savedEmployee = template.save(employee);

        Assert.assertNotSame(savedEmployee.getClass(), Employee.class);
        Assert.assertEquals(template.getRid(savedEmployee), savedEmployee.getRid());
    }
}
