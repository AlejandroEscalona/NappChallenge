package com.example.napptiluschallenge

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    UserFlowTest::class
)
class TestSuite

