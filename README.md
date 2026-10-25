# fcitx5-android · 个人定制版

基于 [fxliang/fcitx5-android](https://github.com/fxliang/fcitx5-android)（`fx` 分支，基线提交 `57cb3517`）的个人定制构建，只服务自己的日常使用，不保证适合所有人。

![数字键盘布局](docs/numpad-layout.jpg)

## 本仓库改了什么

### 1. 数字键盘重排：4 行 × 5 列

| 行 | 按键 | 说明 |
|---|---|---|
| 1 | `+` `1` `2` `3` `⌫` | |
| 2 | `-` `4` `5` `6` `␣` | 空格用 `MiniSpaceKey`（矮条） |
| 3 | `*` `7` `8` `9` `@` | `@` 走 `SymbolKey`，直接写字面 `@` |
| 4 | `!?#` `ABC` `0` `.` `⏎` | |

- 四行统一 5 列网格：首列、末列各 `0.15`，中间三格约 `0.2333`，四行竖线完全对齐。
- `ABC`、`0`、`.` 用弹性宽度（`percentWidth = 0f`）平分剩余空间。
- `+` `-` `*` 从 `NumPadKey` 换成 `SymbolKey`：前者会给按键绑定 `NumLockState`（数字区专用状态），符号键不需要。

### 2. `+` / `-` / `*` 上滑出符号

- `+` 上滑 → `=`，`-` 上滑 → `/`，`*` 上滑 → `%`。
- 与上游的差异：上游的划动符号方向跟随主题里的「标点位置」（默认 `底部`，也就是只能**下滑**出符号）。这里给 `Appearance.AltText` 加了 `swipeUpAltText` 标记，标记过的键**只认上滑**，提示字符固定画在右上角，不受「标点位置」设置影响（把「标点位置」设为 `无` 时提示隐藏，但上滑依然可用）。
- 改动落在 5 个文件：
  - `app/src/main/java/org/fcitx/fcitx5/android/input/keyboard/NumberKeyboard.kt` —— 布局本体；
  - `.../KeyDef.kt` —— `Appearance.AltText` 新增 `swipeUpAltText: Boolean = false`；
  - `.../KeyDefPreset.kt` —— `SymbolKey` 新增 `swipeUp` 参数并透传；
  - `.../KeyView.kt` —— `AltTextKeyView` 固定右上布局、只认上滑；
  - `.../BaseKeyboard.kt` —— 三处 `Appearance.AltText(...)` 拷贝补字段，防止主题/颜色重算把标记丢掉。

## 构建

Windows 实测可用的工具链：

| 组件 | 版本 / 位置 |
|---|---|
| JDK | Temurin 21.0.12.1+1 |
| Android SDK | platform 36、build-tools 36.1.0、platform-tools |
| NDK | 28.0.13004108 |
| CMake | 3.31.6 |
| Gradle | 9.6.1（wrapper） |
| 额外依赖 | ECM 6.31.0（`ECM_DIR`）、GNU gettext 1.0（`msgfmt` / `msgmerge` 必须在 PATH） |

19 个 submodule 必须全部初始化（含 `lib/fcitx5/src/main/cpp/prebuilt`）：

```bash
git submodule update --init --recursive
```

构建：

```bash
./gradlew :app:assembleDebug -PbuildABI=arm64-v8a
```

产物在 `app/build/outputs/apk/fx/debug/`（构建后会自动复制一份到 `app/build/outputs/apk/debug/`）。默认构建 `fx` 变体，但包名改成了自己的 `org.fcitx.fcitx5.android.vgh`（debug 为 `…vgh.debug`），可以和上游包/官方 fx 包并存；配套插件要用 `-PmainApplicationId=org.fcitx.fcitx5.android.vgh` 编译，插件才认这个宿主。要上游同名变体加 `-PincludeMainlineFlavor=true` 并改用 `:app:assembleMainlineDebug`。

## 许可

LGPL-2.1-or-later，遵循上游。本仓库的改动部分同样以 LGPL-2.1-or-later 发布。
