FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# v10 (HWC API 1.0) ambient/LPM support depends on minnow's DSI command-mode
# panel smart-ambient self-refresh (the 'interactivemode' sysfs node), so it is
# applied for this machine only rather than for every platform that happens to
# use the v10 backend.
SRC_URI:append:minnow = " file://0010-v10-Support-ambient-LPM-mode-via-panel-self-refresh.patch;striplevel=2"
