inherit gettext

SUMMARY = "Downloads the Snapdragon Wear 2100/3100 /usr/libexec/hal-droid and /usr/include/android folders and installs them for libhybris"
LICENSE = "CLOSED"

#SRC_URI = "file://hybris-o-msm8909.tar.gz;name=hybris 
# New V3 with the TLS fixes
#https://dl.dropboxusercontent.com/s/4d8pkmd62rxl464/hybris-o-msm8909.tar.gz
#SRC_URI = "https://dl.dropboxusercontent.com/s/8b9t2renrxbl4gq/hybris-o-msm8909.tar.gz;name=hybris 
SRC_URI = "file://hybris-o-msm8909.tar.gz;name=hybris \
    file://audio_policy.conf"
# SRC_URI[hybris.md5sum] = "8ef12e6fae59e7656d9a7bc39560f7f2"
# SRC_URI[hybris.sha256sum] = "98ae0a6a7f93f82a0cfb73fa914773ce15884c187641a72c57de6bbb39062c3a"
# SRC_URI[hybris.md5sum] = "edc1f8304b58af335a9c9ba8136bc1b8"
# SRC_URI[hybris.sha256sum] = "626bed275cfe2df2377e709498fc26d58e7883045cd13d4e2a6284220d1113b0"
SRC_URI[hybris.md5sum] = "039a387a3e4ccd09d28b05195280d162"
SRC_URI[hybris.sha256sum] = "ddc725dd280d8c5f546ff47f727b67ea99def9a4883edeebfe0edfdc5a61a636"
PV = "oreo"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"
COMPATIBLE_MACHINE = "mullet"
INSANE_SKIP:${PN} = "already-stripped"
S = "${WORKDIR}"
B = "${S}"

PROVIDES += "virtual/android-system-image"
PROVIDES += "virtual/android-headers"

do_install() {
    # The stock audio policy contains invalid entries that cause the droid module to fail.
    install -d ${D}${sysconfdir}/pulse
    install -m 0644 ${WORKDIR}/audio_policy.conf ${D}${sysconfdir}/pulse/

    install -d ${D}/usr/
    cp -r usr/* ${D}/usr/

    install -d ${D}${includedir}/android
    cp -r include/* ${D}${includedir}/android/

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${D}${includedir}/android/android-headers.pc ${D}${libdir}/pkgconfig
    rm ${D}${includedir}/android/android-headers.pc
}

# FIXME: QA Issue: Architecture did not match (40 to 164) on /work/dory-oe-linux-gnueabi/android/lollipop-r0/packages-split/android-system/system/vendor/firmware/adsp.b00 [arch]
do_package_qa() {
}

PACKAGES =+ "android-system android-headers"
FILES:android-system = "/usr ${sysconfdir}/pulse/"
FILES:android-headers = "${libdir}/pkgconfig ${includedir}/android"
EXCLUDE_FROM_SHLIBS = "1"
