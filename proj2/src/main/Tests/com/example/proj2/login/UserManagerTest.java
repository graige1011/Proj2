package com.example.proj2.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    UserManager userManager = new UserManager();

    //CC
    @Test
    public void TestCCValidateRegistration() {
        // Testcase 1: username.isEmpty() is false, password.length() >= 4 is false
        String username1 = "john";
        String password1 = "abc";
        boolean expectedResult1 = false;

        boolean actualResult1 = userManager.validateRegistration(username1, password1);
        assertEquals(expectedResult1, actualResult1);

        // Testcase 2: username.isEmpty() is true, password.length() >= 4 is true
        String username2 = "";
        String password2 = "abcd";
        boolean expectedResult2 = true;

        boolean actualResult2 = userManager.validateRegistration(username2, password2);
        assertEquals(expectedResult2, actualResult2);

    }

    //DC
    @Test
    public void TestDCValidateRegistration() {
        // Testcase 1: username.isEmpty() is false, password.length() >= 4 is false
        String username1 = "john";
        String password1 = "abc";
        boolean expectedResult1 = false;

        boolean actualResult1 = userManager.validateRegistration(username1, password1);
        assertEquals(expectedResult1, actualResult1);

        // Testcase 2: username.isEmpty() is true, password.length() >= 4 is true
        String username2 = "";
        String password2 = "abcd";
        boolean expectedResult2 = true;

        boolean actualResult2 = userManager.validateRegistration(username2, password2);
        assertEquals(expectedResult2, actualResult2);

    }

    @Test
    public void TestMCCValidateRegistration() {
        UserManager userManager = new UserManager();

        // Testcase 1: A = 0 (false), B = 0 (false), D = 0 (false)
        String username1 = "john";
        String password1 = "abc";
        boolean expectedResult1 = false;

        boolean actualResult1 = userManager.validateRegistration(username1, password1);
        assertEquals(expectedResult1, actualResult1);

        // Testcase 2: A = 0 (false), B = 1 (true), D = 1 (true)
        String username2 = "john";
        String password2 = "abcd";
        boolean expectedResult2 = true;

        boolean actualResult2 = userManager.validateRegistration(username2, password2);
        assertEquals(expectedResult2, actualResult2);

        // Testcase 3: A = 1 (true), B = 0 (false), D = 0 (false)
        String username3 = "";
        String password3 = "abc";
        boolean expectedResult3 = false;

        boolean actualResult3 = userManager.validateRegistration(username3, password3);
        assertEquals(expectedResult3, actualResult3);

        // Testcase 4: A = 1 (true), B = 1 (true), D = 0 (false)
        String username4 = "";
        String password4 = "abcd";
        boolean expectedResult4 = false;

        boolean actualResult4 = userManager.validateRegistration(username4, password4);
        assertEquals(expectedResult4, actualResult4);
    }
    @Test
    public void TestMCDCValidateRegistration() {
        // Testcase 1: A = 0 (false), B = 1 (true), D = 1(true)
        String username1 = "john";
        String password1 = "abcd";
        boolean expectedResult1 = true;

        boolean actualResult1 = userManager.validateRegistration(username1, password1);
        assertEquals(expectedResult1, actualResult1);

        // Testcase 2: A = 1 (true), B = 0 (false), D = 0 (false)
        String username2 = "";
        String password2 = "abc";
        boolean expectedResult2 = false;

        boolean actualResult2 = userManager.validateRegistration(username2, password2);
        assertEquals(expectedResult2, actualResult2);

        // Testcase 3: A = 1 (true), B = 1 (abcd), D = 0 (false)
        String username3 = "";
        String password3 = "abcd";
        boolean expectedResult3 = false;

        boolean actualResult3 = userManager.validateRegistration(username3, password3);
        assertEquals(expectedResult3, actualResult3);


    }


}

