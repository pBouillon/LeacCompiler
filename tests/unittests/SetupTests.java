package unittests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Setup unit tests
 * Those tests aims to assert that the project is correctly set up
 *
 * @author Florian Vogt
 * @author Pierre Bouillon
 * @author Victor Varnier
 *
 * @url https://github.com/pBouillon/TELECOM_Trad
 * @version 0.1
 */
public class SetupTests {

    /**
     * Setup for each test
     */
    @BeforeEach
    void init() { }

    /**
     * Setup for test class
     */
    @BeforeAll
    static void initAll() { }

    /**
     * Cleanup after each test
     */
    @AfterEach
    void tearDown() { }

    /**
     * Cleanup after the execution of this test class
     */
    @AfterAll
    static void tearDownAll() { }

    /**
     * Default setup test
     *
     * An empty test that should pass no matter what
     * This test aims to assert that the CI and/or the project is correctly set up
     */
    @Test
    void SetupTest() { }

}
