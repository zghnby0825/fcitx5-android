/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * SPDX-FileCopyrightText: Copyright 2021-2023 Fcitx5 for Android Contributors
 */
package org.fcitx.fcitx5.android.input.keyboard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import org.fcitx.fcitx5.android.R
import org.fcitx.fcitx5.android.data.theme.Theme
import org.fcitx.fcitx5.android.input.picker.PickerWindow
import org.fcitx.fcitx5.android.input.popup.PopupAction
import splitties.views.imageResource

@SuppressLint("ViewConstructor")
class NumberKeyboard(
    context: Context,
    theme: Theme,
) : BaseKeyboard(context, theme, ::Layout, { null }) {

    override fun currentLayoutSignature(): String = Name

    companion object {
        const val Name = "Number"

        val Layout: List<List<KeyDef>> = listOf(
            listOf(
                SymbolKey(
                    "+", 0.15f, KeyDef.Appearance.Variant.Alternative,
                    popup = arrayOf(),
                    swipe = MacroAction(listOf(MacroStep.Text("="))),
                    swipeLabel = "=",
                    swipeUp = true
                ),
                NumPadKey("1", 0xffb1, 30f, 0f),
                NumPadKey("2", 0xffb2, 30f, 0f),
                NumPadKey("3", 0xffb3, 30f, 0f),
                BackspaceKey()
            ),
            listOf(
                SymbolKey(
                    "-", 0.15f, KeyDef.Appearance.Variant.Alternative,
                    popup = arrayOf(),
                    swipe = MacroAction(listOf(MacroStep.Text("/"))),
                    swipeLabel = "/",
                    swipeUp = true
                ),
                NumPadKey("4", 0xffb4, 30f, 0f),
                NumPadKey("5", 0xffb5, 30f, 0f),
                NumPadKey("6", 0xffb6, 30f, 0f),
                MiniSpaceKey()
            ),
            listOf(
                SymbolKey(
                    "*", 0.15f, KeyDef.Appearance.Variant.Alternative,
                    popup = arrayOf(),
                    swipe = MacroAction(listOf(MacroStep.Text("%"))),
                    swipeLabel = "%",
                    swipeUp = true
                ),
                NumPadKey("7", 0xffb7, 30f, 0f),
                NumPadKey("8", 0xffb8, 30f, 0f),
                NumPadKey("9", 0xffb9, 30f, 0f),
                // 符号键用 SymbolKey（FcitxKeyAction 直接写字面 '@'），NumPadKey 会附带 NumLock 状态
                SymbolKey("@", 0.15f, KeyDef.Appearance.Variant.Alternative, arrayOf())
            ),
            listOf(
                LayoutSwitchKey("!?#", PickerWindow.Key.Symbol.name, 0.15f, KeyDef.Appearance.Variant.AltForeground),
                LayoutSwitchKey("ABC", TextKeyboard.Name, 0f),
                NumPadKey("0", 0xffb0, 30f, 0f),
                NumPadKey(".", 0xffae, 23f, 0f, KeyDef.Appearance.Variant.Alternative),
                ReturnKey()
            )
        )
    }

    val backspace: ImageKeyView? by lazy { findKeyViewById<ImageKeyView>(R.id.button_backspace) }
    val space: ImageKeyView? by lazy { findKeyViewById<ImageKeyView>(R.id.button_mini_space) }
    val `return`: ImageKeyView? by lazy { findKeyViewById<ImageKeyView>(R.id.button_return) }

    override fun onReturnDrawableUpdate(returnDrawable: Int) {
        `return`?.img?.imageResource = returnDrawable
    }

    override fun onReturnDrawableOverride(drawable: Drawable?) {
        if (drawable != null) {
            `return`?.img?.setImageDrawable(drawable)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onPopupAction(action: PopupAction) {
        // leave empty on purpose to disable popup in NumberKeyboard
    }

}
