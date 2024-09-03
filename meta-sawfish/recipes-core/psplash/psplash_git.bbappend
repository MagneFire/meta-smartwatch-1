FILESEXTRAPATHS:prepend:sawfish := "${THISDIR}/${PN}:"
SRC_URI:append:sawfish = " file://0002-Disable-double-buffering.patch"

do_install:append:sawfish() {
    install -d ${D}/usr/share/
    install -m 0755 ${WORKDIR}/psplash-img-400-220.gif ${D}/usr/share/psplash.gif
}

# SPLASH_IMAGES = "file://psplash-img-280.png;outsuffix=default"
