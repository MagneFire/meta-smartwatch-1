inherit gettext

SUMMARY = "Downloads the Huawei Watch 2 system folders and installs them for libhybris"
LICENSE = "CLOSED"
SRC_URI = "https://www.dropbox.com/scl/fi/sx1bfsbjejuz4ro5y8icw/system-sawfish-pie-preview.tar.gz?rlkey=7wxxf9l24cd2ip77wu1mnqkij&st=47359t8m&dl=0;downloadfilename=system-sawfish-pie-preview.tar.gz"
SRC_URI[md5sum] = "9c99e1494ee6c90e2584724295f102d2"
SRC_URI[sha256sum] = "6f6678adf70325d23cc3cb89edb1a2fd80eb22455d3d2148eecd9eb7d7fa1028"
PV = "pie"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"
COMPATIBLE_MACHINE = "sawfish"
INSANE_SKIP:${PN} = "ldflags dev-so already-stripped"
S = "${WORKDIR}"
B = "${S}"

PROVIDES += "virtual/android-system-partition"

do_install() {
    install -d ${D}/system/
    cp -r system/* ${D}/system/

    cd ${D}
    ln -s system/vendor vendor
}

do_package_qa() {
}

FILES:${PN} = "/system /vendor"
EXCLUDE_FROM_SHLIBS = "1"
