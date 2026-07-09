FILESEXTRAPATHS:prepend:swift := "${THISDIR}/${PN}:"

SRC_URI:append:swift = " file://bt-init.sh"

do_install:append:swift() {
    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/bt-init.sh ${D}${bindir}/bt-init.sh
}

# bt-init.sh runs with the Android MM shell from /system
INSANE_SKIP:${PN}:append:swift = " file-rdeps"
