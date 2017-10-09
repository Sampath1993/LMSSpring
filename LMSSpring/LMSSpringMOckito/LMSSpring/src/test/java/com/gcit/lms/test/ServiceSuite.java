package com.gcit.lms.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.gcit.lms.test.services.TestAuthorServices;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestAuthorServices.class})
public class ServiceSuite {

}
