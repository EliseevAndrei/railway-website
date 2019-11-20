package com.eliseev.app.db.dao;

import com.eliseev.app.models.Train;
import com.eliseev.app.repository.custom.TrainDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainDAOTest {

    @Autowired
    private TrainDAO trainDAO;

    @Test
    @Transactional
    public void count() {
        assertEquals(2, trainDAO.count());
    }

    @Test
    @Transactional
    public void findOne() {
        Train train = trainDAO.findOne(1);
        assertEquals("10в", train.getName());
        assertEquals(10, train.getCountCommon());
        assertEquals(10, train.getCountCoupe());
        assertEquals(10, train.getCountLying());
    }

    @Test
    @Transactional
    public void save() {
        assertEquals(2, trainDAO.count());
        Train train = new Train("200B", 11, 11, 11);
        trainDAO.save(train);
        assertEquals(3, trainDAO.count());
    }

    @Test
    @Transactional
    public void delete() {
        assertNotNull(trainDAO.findOne(1));
        trainDAO.delete(1);
        assertNull(trainDAO.findOne(1));
    }

    @Test
    @Transactional
    public void update() {
        assertEquals(2, trainDAO.count());
        Train train = new Train("200B", 11, 11, 11);
        trainDAO.save(train);
        assertEquals(3, trainDAO.count());
    }

    @Test
    @Transactional
    public void departmentRepositoryTest() {

        Train train = new Train("200B", 11, 11, 11);

        trainDAO.save(train);

    }

}
