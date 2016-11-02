package com.pathfinder.data.orient.object;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * Created by IWC-NeilDunlop on 01/11/2016.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {OrientDbObjectTestConfiguration.class})
public class OrientObjectDatabaseFactoryTest {


    @Autowired
    OrientObjectDatabaseFactory factory;

    //Gives you access to the current test name inside the test.
    //@Rule
    //public TestName name = new TestName();


    @Test
    public void shouldCountClassElements() throws Exception {

        OObjectDatabaseTx db = factory.db();

        assertEquals(3, db.countClass("OUser"));
    }

    @Test
    public void shouldCountClusterElements() throws Exception {

        OObjectDatabaseTx db = factory.db();

        assertEquals(3, db.countClusterElements("OUser"));
    }
}
