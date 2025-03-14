package com.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ArrayStorageTest.class,
                ListStorageTest.class,
                MapStorageTest.class,
                NameMapStorageTest.class,
                SortedArrayStorageTest.class,
                AbstractArrayStorageTest.class,
        })
public class AllStorageTest {
}