package com.seiko.greenqrscanner.ui

import io.github.seiko.precompose.annotation.Ignore
import io.github.seiko.precompose.annotation.NavGraphContainer
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
@NavGraphContainer
internal fun RouteBuilder.generateRoute(
    navigator: Navigator,
    @Ignore doNotUse: DoNotUse = DoNotUse,
) = Unit

@Deprecated("Don't Use")
object DoNotUse
