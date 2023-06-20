package androidx.compose.material3

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.SheetValue.Expanded
import androidx.compose.material3.SheetValue.Hidden
import androidx.compose.material3.SheetValue.PartiallyExpanded
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun BottomSheetScaffold(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight,
    sheetShape: Shape = BottomSheetDefaults.ExpandedShape,
    sheetContainerColor: Color = BottomSheetDefaults.ContainerColor,
    sheetContentColor: Color = contentColorFor(sheetContainerColor),
    sheetTonalElevation: Dp = BottomSheetDefaults.Elevation,
    sheetShadowElevation: Dp = BottomSheetDefaults.Elevation,
    sheetDragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    sheetSwipeEnabled: Boolean = true,
    topBar: @Composable (() -> Unit)? = null,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable (PaddingValues) -> Unit
) {
    BottomSheetScaffoldLayout(
        modifier = modifier,
        topBar = topBar,
        body = content,
        snackbarHost = {
            snackbarHost(scaffoldState.snackbarHostState)
        },
        sheetPeekHeight = sheetPeekHeight,
        sheetOffset = { scaffoldState.bottomSheetState.requireOffset() },
        sheetState = scaffoldState.bottomSheetState,
        containerColor = containerColor,
        contentColor = contentColor,
        bottomSheet = { layoutHeight ->
            StandardBottomSheet(
                state = scaffoldState.bottomSheetState,
                peekHeight = sheetPeekHeight,
                sheetSwipeEnabled = sheetSwipeEnabled,
                layoutHeight = layoutHeight.toFloat(),
                shape = sheetShape,
                containerColor = sheetContainerColor,
                contentColor = sheetContentColor,
                tonalElevation = sheetTonalElevation,
                shadowElevation = sheetShadowElevation,
                dragHandle = sheetDragHandle,
                content = sheetContent,
            )
        },
    )
}

@Stable
class BottomSheetScaffoldState(
    val bottomSheetState: SheetState,
    val snackbarHostState: SnackbarHostState
)

@Composable
fun rememberBottomSheetScaffoldState(
    bottomSheetState: SheetState = rememberStandardBottomSheetState(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): BottomSheetScaffoldState {
    return remember(bottomSheetState, snackbarHostState) {
        BottomSheetScaffoldState(
            bottomSheetState = bottomSheetState,
            snackbarHostState = snackbarHostState,
        )
    }
}

@Composable
fun rememberStandardBottomSheetState(
    initialValue: SheetValue = PartiallyExpanded,
    confirmValueChange: (SheetValue) -> Boolean = { true },
    skipHiddenState: Boolean = true,
) = rememberSheetState(false, confirmValueChange, initialValue, skipHiddenState)

@Composable
private fun StandardBottomSheet(
    state: SheetState,
    peekHeight: Dp,
    sheetSwipeEnabled: Boolean,
    layoutHeight: Float,
    shape: Shape,
    containerColor: Color,
    contentColor: Color,
    tonalElevation: Dp,
    shadowElevation: Dp,
    dragHandle: @Composable (() -> Unit)?,
    content: @Composable ColumnScope.() -> Unit
) {
    val scope = rememberCoroutineScope()
    val peekHeightPx = with(LocalDensity.current) { peekHeight.toPx() }
    val orientation = Orientation.Vertical

    // Callback that is invoked when the anchors have changed.
    val anchorChangeHandler = remember(state, scope) {
        BottomSheetScaffoldAnchorChangeHandler(
            state = state,
            animateTo = { target, velocity ->
                scope.launch {
                    state.swipeableState.animateTo(
                        target,
                        velocity = velocity,
                    )
                }
            },
            snapTo = { target ->
                scope.launch { state.swipeableState.snapTo(target) }
            },
        )
    }
    Surface(
        modifier = Modifier
            .widthIn(max = BottomSheetMaxWidth)
            .fillMaxWidth()
            .requiredHeightIn(min = peekHeight)
            .nestedScroll(
                remember(state.swipeableState) {
                    ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(
                        sheetState = state,
                        orientation = orientation,
                        onFling = { scope.launch { state.settle(it) } },
                    )
                },
            )
            .swipeableV2(
                state = state.swipeableState,
                orientation = orientation,
                enabled = sheetSwipeEnabled,
            )
            .swipeAnchors(
                state.swipeableState,
                possibleValues = setOf(Hidden, PartiallyExpanded, Expanded),
                anchorChangeHandler = anchorChangeHandler,
            ) { value, sheetSize ->
                when (value) {
                    PartiallyExpanded -> if (state.skipPartiallyExpanded) {
                        null
                    } else layoutHeight - peekHeightPx
                    Expanded -> if (sheetSize.height == peekHeightPx.roundToInt()) {
                        null
                    } else {
                        // 0f
                        max(0f, layoutHeight - sheetSize.height)
                    }
                    Hidden -> if (state.skipHiddenState) null else layoutHeight
                }
            },
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
    ) {
        Column(Modifier.fillMaxWidth()) {
            if (dragHandle != null) {
                val partialExpandActionLabel = getString2(Strings.BottomSheetPartialExpandDescription)
                val dismissActionLabel = getString2(Strings.BottomSheetDismissDescription)
                val expandActionLabel = getString2(Strings.BottomSheetExpandDescription)
                Box(
                    Modifier
                        .align(CenterHorizontally)
                        .semantics(mergeDescendants = true) {
                            with(state) {
                                // Provides semantics to interact with the bottomsheet if there is more
                                // than one anchor to swipe to and swiping is enabled.
                                if (swipeableState.anchors.size > 1 && sheetSwipeEnabled) {
                                    if (currentValue == PartiallyExpanded) {
                                        if (swipeableState.confirmValueChange(Expanded)) {
                                            expand(expandActionLabel) {
                                                scope.launch { expand() }; true
                                            }
                                        }
                                    } else {
                                        if (swipeableState.confirmValueChange(PartiallyExpanded)) {
                                            collapse(partialExpandActionLabel) {
                                                scope.launch { partialExpand() }; true
                                            }
                                        }
                                    }
                                    if (!state.skipHiddenState) {
                                        dismiss(dismissActionLabel) {
                                            scope.launch { hide() }
                                            true
                                        }
                                    }
                                }
                            }
                        },
                ) {
                    dragHandle()
                }
            }
            content()
        }
    }
}

@Composable
private fun BottomSheetScaffoldLayout(
    modifier: Modifier,
    topBar: @Composable (() -> Unit)?,
    body: @Composable (innerPadding: PaddingValues) -> Unit,
    bottomSheet: @Composable (layoutHeight: Int) -> Unit,
    snackbarHost: @Composable () -> Unit,
    sheetPeekHeight: Dp,
    sheetOffset: () -> Float,
    sheetState: SheetState,
    containerColor: Color,
    contentColor: Color,
) {
    SubcomposeLayout { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val sheetPlaceable = subcompose(BottomSheetScaffoldLayoutSlot.Sheet) {
            bottomSheet(layoutHeight)
        }[0].measure(looseConstraints)
        val sheetOffsetY = sheetOffset().roundToInt()
        val sheetOffsetX = max(0, (layoutWidth - sheetPlaceable.width) / 2)

        val topBarPlaceable = topBar?.let {
            subcompose(BottomSheetScaffoldLayoutSlot.TopBar) { topBar() }[0]
                .measure(looseConstraints)
        }
        val topBarHeight = topBarPlaceable?.height ?: 0

        val bodyConstraints = looseConstraints.copy(maxHeight = layoutHeight - topBarHeight)
        val bodyPlaceable = subcompose(BottomSheetScaffoldLayoutSlot.Body) {
            Surface(
                modifier = modifier,
                color = containerColor,
                contentColor = contentColor,
            ) { body(PaddingValues(bottom = sheetPeekHeight)) }
        }[0].measure(bodyConstraints)

        val snackbarPlaceable = subcompose(BottomSheetScaffoldLayoutSlot.Snackbar, snackbarHost)[0]
            .measure(looseConstraints)
        val snackbarOffsetX = (layoutWidth - snackbarPlaceable.width) / 2
        val snackbarOffsetY = when (sheetState.currentValue) {
            PartiallyExpanded -> sheetOffsetY - snackbarPlaceable.height
            Expanded, Hidden -> layoutHeight - snackbarPlaceable.height
        }

        layout(layoutWidth, layoutHeight) {
            // Placement order is important for elevation
            bodyPlaceable.placeRelative(0, topBarHeight)
            topBarPlaceable?.placeRelative(0, 0)
            sheetPlaceable.placeRelative(sheetOffsetX, sheetOffsetY)
            snackbarPlaceable.placeRelative(snackbarOffsetX, snackbarOffsetY)
        }
    }
}

private fun BottomSheetScaffoldAnchorChangeHandler(
    state: SheetState,
    animateTo: (target: SheetValue, velocity: Float) -> Unit,
    snapTo: (target: SheetValue) -> Unit,
) = AnchorChangeHandler<SheetValue> { previousTarget, previousAnchors, newAnchors ->
    val previousTargetOffset = previousAnchors[previousTarget]
    val newTarget = when (previousTarget) {
        Hidden, PartiallyExpanded -> PartiallyExpanded
        Expanded -> if (newAnchors.containsKey(Expanded)) Expanded else PartiallyExpanded
    }
    val newTargetOffset = newAnchors.getValue(newTarget)
    if (newTargetOffset != previousTargetOffset) {
        if (state.swipeableState.isAnimationRunning) {
            // Re-target the animation to the new offset if it changed
            animateTo(newTarget, state.swipeableState.lastVelocity)
        } else {
            // Snap to the new offset value of the target if no animation was running
            snapTo(newTarget)
        }
    }
}

private enum class BottomSheetScaffoldLayoutSlot { TopBar, Body, Sheet, Snackbar }
