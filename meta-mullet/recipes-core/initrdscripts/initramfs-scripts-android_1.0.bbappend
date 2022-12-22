FILESEXTRAPATHS:prepend:mullet := "${THISDIR}/${PN}:"
COMPATIBLE_MACHINE:mullet = "mullet"

RDEPENDS:${PN}:append:mullet = " msm-fb-refresher"
