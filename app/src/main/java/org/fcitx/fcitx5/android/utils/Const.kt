/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * SPDX-FileCopyrightText: Copyright 2021-2023 Fcitx5 for Android Contributors
 */
package org.fcitx.fcitx5.android.utils

import org.fcitx.fcitx5.android.BuildConfig

object Const {
    const val versionName = "${BuildConfig.VERSION_NAME}"
    // 本定制版仓库：关于页「源代码」与版本检查都指向这里（上游是 fxliang/fcitx5-android）
    const val githubRepo = "https://github.com/zghnby0825/fcitx5-android"
    const val licenseSpdxId = "LGPL-2.1-or-later"
    const val licenseUrl = "https://www.gnu.org/licenses/old-licenses/lgpl-2.1"
    const val privacyPolicyUrl = "https://fcitx5-android.github.io/privacy/"
    const val faqUrl = "https://fcitx5-android.github.io/faq/"
}
