# SRCREV:mullet = "8883e9aca7de870a4a93b31e82cfc600c51cef5d"
FILESEXTRAPATHS:prepend:mullet := "${THISDIR}/libhybris:"

LIC_FILES_CHKSUM:mullet = "file://../LICENSE.Apache2;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI:append:mullet = " file://0001-hybris-egl-Provide-eglCreatePlatformWindowSurface.patch;patchdir=.."
SRC_URI:remove:mullet = "file://0001-Add-EGL_OPENGL_ES3_BIT_KHR-define.patch;patchdir=.."
SRC_URI:remove:mullet = "file://0001-wayland-egl.pc.in-bump-Version-from-libhybris-s-0.1..patch;patchdir=.."
SRCREV:mullet = "66782eec69a310d5e8fed12bda3fa2a14ca58e64"
