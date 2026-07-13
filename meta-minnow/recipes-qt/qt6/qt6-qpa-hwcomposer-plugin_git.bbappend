FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# v10 (HWC API 1.0) ambient/LPM support depends on minnow's DSI command-mode
# panel smart-ambient self-refresh (the 'interactivemode' sysfs node), so it is
# applied for this machine only rather than for every platform that happens to
# use the v10 backend.
SRC_URI:append:minnow = " file://0010-v10-Support-ambient-LPM-mode-via-panel-self-refresh.patch;striplevel=2"

# The command-mode panel self-paces frame commits through its TE signal, so the
# extra pre-set() vsync wait in swap() was pure latency (~34 -> ~44 fps).
SRC_URI:append:minnow = " file://0011-v10-Drop-the-redundant-pre-set-vsync-wait-on-minnow.patch;striplevel=2"
