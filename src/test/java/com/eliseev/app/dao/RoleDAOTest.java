package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.Role;
import com.eliseev.app.models.UserRoleEnum;
import com.eliseev.app.repository.custom.RoleDAO;
import com.eliseev.app.repository.custom.UserDAO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RoleDAOTest {

    private Logger logger = LoggerFactory.getLogger(RoleDAOTest.class);

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    @Transactional
    public void findAll() {
        List<Role> roles = roleDAO.findAll();
        assertEquals(3, roles.size());
        assertRole(0, roles.get(0));
        assertRole(1, roles.get(1));
        assertRole(2,  roles.get(2));
    }

    @Test
    @Transactional
    public void findOne() {
        assertRole(0, roleDAO.findOne(1L));
    }

    @Test
    @Transactional
    public void count() {
        assertEquals(3, roleDAO.count());
    }

    @Test
    @Transactional
    public void updateExistingRole() {
        assertEquals(3, roleDAO.count());
        Role role = new Role(1, UserRoleEnum.USER);
        Role saved = roleDAO.save(role);
        assertRole(0, saved);
        assertEquals(3, roleDAO.count());
        Role updated = roleDAO.findOne(1L);
        assertRole(0, updated);
    }

    @Test
    @Transactional
    public void delete() {
        assertEquals(3, roleDAO.count());
        assertEquals(2, userDAO.count());
        roleDAO.delete(1L);
        assertEquals(2, roleDAO.count());
        assertEquals(2, userDAO.count());
    }

    @Test
    @Transactional
    public void getRoleByName() {
        assertRole(0, roleDAO.findByName(UserRoleEnum.USER));
    }

    private static void assertRole(int expectedRoleIndex, Role actual) {
        Role expected = ROLES[expectedRoleIndex];
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    private static Role[] ROLES = new Role[3];

    @BeforeClass
    public static void before() {

        ROLES[0] = new Role(1L, UserRoleEnum.USER);
        ROLES[1] = new Role(2L, UserRoleEnum.ADMIN);
        ROLES[2] = new Role(3L, UserRoleEnum.ANONYMOUS);

    }

}
