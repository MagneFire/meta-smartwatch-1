do_install:append:minnow() {
    install -d ${D}/usr/share/
    install -m 0755 ${WORKDIR}/psplash-img-320-176.gif ${D}/usr/share/psplash.gif
}
