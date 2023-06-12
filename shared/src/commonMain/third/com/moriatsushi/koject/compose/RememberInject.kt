/*
 *  Mask-Android
 *
 *  Copyright (C) 2022 DimensionDev and Contributors
 *
 *  This file is part of Mask X.
 *
 *  Mask X is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Mask-Android is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with Mask-Android.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.inject

@Composable
inline fun <reified T : Any> rememberInject(): T {
    return remember {
        inject()
    }
}

@OptIn(ExperimentalKojectApi::class)
@Composable
inline fun <reified T : Any> rememberInject(
    componentExtras: ComponentExtras<*>? = null,
): T {
    return remember {
        inject(componentExtras = componentExtras)
    }
}

@OptIn(ExperimentalKojectApi::class)
@Composable
inline fun <reified T : Any> rememberInject(
    qualifier: Any?,
    componentExtras: ComponentExtras<*>? = null,
): T {
    return remember(qualifier) {
        inject(qualifier, componentExtras = componentExtras)
    }
}
