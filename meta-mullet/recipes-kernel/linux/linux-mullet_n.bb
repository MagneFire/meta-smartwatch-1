require recipes-kernel/linux/linux.inc
inherit gettext

SECTION = "kernel"
SUMMARY = "Android kernel for the Misfit Vapor"
HOMEPAGE = "https://android.googlesource.com/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "mullet"

# file://0003-Backport-mainline-4.1-Bluetooth-subsystem.patch 
# file://0004-Backport-mainline-4.1-Bluetooth-drivers.patch 
# file://0005-video-mdp3-Continue-when-the-overlay-wasn-t-released.patch 

SRC_URI = " git://android.googlesource.com/kernel/msm;branch=android-msm-mullet-3.18-oreo-wear-dr;protocol=https \
    file://defconfig \
    file://img_info \
    file://0001-scripts-dtc-Remove-redundant-YYLOC-global-declaratio.patch \
    file://0002-ARM-uaccess-remove-put_user-code-duplication.patch \
"

SRCREV = "b7157db4217f9ef1c5a4c9ef45ec35913ca77a88"
LINUX_VERSION ?= "3.18"
PV = "${LINUX_VERSION}+oreo"
S = "${WORKDIR}/git"
B = "${S}"

do_install:append() {
    rm -rf ${D}/usr/src/usr/
}

# BOOT_PARTITION = "/dev/mmcblk0p35"

inherit mkboot old-kernel-gcc-hdrs
