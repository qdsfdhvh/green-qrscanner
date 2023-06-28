package androidx.compose.animation

@OptIn(ExperimentalAnimationApi::class)
infix fun EnterTransition.togetherWith(exit: ExitTransition) = ContentTransform(this, exit)
