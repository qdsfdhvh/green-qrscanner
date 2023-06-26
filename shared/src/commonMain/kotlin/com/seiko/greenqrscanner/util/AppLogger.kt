package com.seiko.greenqrscanner.util

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.SimpleFormatter
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter

object AppLogger : Logger(
    config = loggerConfigInit(
        platformLogWriter(SimpleFormatter),
        minSeverity = Severity.Debug,
    ),
    tag = "GreenScanner",
)
