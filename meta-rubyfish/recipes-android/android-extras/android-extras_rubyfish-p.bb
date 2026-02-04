inherit gettext

SUMMARY = "Downloads specific vendor files that are not easily available for the Ticwatch Pro 3."
LICENSE = "CLOSED"
SRC_URI = "https://www.dropbox.com/scl/fi/a07m1g61893ur0tahhp51/libmcutool.so?rlkey=asg9hjhmhh0jy6bdqys6o9zjw&st=r4313z4b&dl=1;name=libmcutool"
SRC_URI[libmcutool.md5sum] = "b821a26b7299edc5a144818bb8279799"
SRC_URI[libmcutool.sha256sum] = "0099b6c16feb9ceef56a5e87d9ff293aed810b827cf1a9c33f6f2ef3e6b3b59d"
PV = "pie"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"
COMPATIBLE_MACHINE = "rubyfish"
INSANE_SKIP:${PN} = "already-stripped"
S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"
B = "${S}"

do_install() {
    install -d ${D}/usr/libexec/hal-droid/system/lib/
    install -m 644 ${UNPACKDIR}/libmcutool.so ${D}/usr/libexec/hal-droid/system/lib/
}

# FIXME: QA Issue: Architecture did not match (40 to 164) on /work/dory-oe-linux-gnueabi/android/lollipop-r0/packages-split/android-system/system/vendor/firmware/adsp.b00 [arch]
do_package_qa() {
}

PACKAGES =+ "android-system-extras"
FILES:android-system = "${sysconfdir}/udev /usr/libexec/hal-droid/system/lib/"
EXCLUDE_FROM_SHLIBS = "1"
