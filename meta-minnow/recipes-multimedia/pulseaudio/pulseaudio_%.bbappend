RDEPENDS:pulseaudio-server:remove:minnow = " pulseaudio-modules-droid-jb2q "

do_install:append:minnow() {
    rm -f ${D}${systemd_user_unitdir}/default.target.wants/pulseaudio.service
}
