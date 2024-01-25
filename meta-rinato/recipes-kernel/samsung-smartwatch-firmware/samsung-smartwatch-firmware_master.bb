SECTION = "kernel"
SUMMARY = "Firmware for Samsung rinato smartwatch"
HOMEPAGE = "https://github.com/casept/linux-samsung-smartwatch-firmware"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = "rinato"

SRC_URI = " git://git@github.com/casept/linux-samsung-smartwatch-firmware.git;protocol=https;branch=master \
    file://fake-copying"

# For random firmware files extracted from wherever, the licensing is of course unclear.
# Just create an empty file and checksum that to make yocto shut up.
LIC_FILES_CHKSUM = "file://fake-copying;md5=d41d8cd98f00b204e9800998ecf8427e"

SRC_URI[sha256sum] = "1c1c2792338577428a0318a1fac1e788941015bf39894a255b8323c5138037c6"
SRCREV = "ec4a73d314ffcf97297567d6f6b20cad0263a989"
PV = "master"
S = "${WORKDIR}/git"

FILES:${PN} += " /lib/firmware "

do_install() {
        install -m 0755 -d ${D}/lib/firmware/
        install -m 0755 -d ${D}/lib/firmware/brcm/
        install -m 0755 -d ${D}/lib/firmware/tsp_melfas/w/
        install -m 0644 ${S}/rinato/brcm/* ${D}/lib/firmware/brcm/
        cp ${S}/rinato/brcm/bcm4334W.hcd ${D}/lib/firmware/brcm/BCM.samsung,rinato.hcd
        install -m 0644 ${S}/rinato/tsp_melfas/w/* ${D}/lib/firmware/tsp_melfas/w/
}
