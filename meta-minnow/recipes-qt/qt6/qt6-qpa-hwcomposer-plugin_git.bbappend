FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# v10 (HWC API 1.0) ambient/LPM support depends on minnow's DSI command-mode
# panel smart-ambient self-refresh (the 'interactivemode' sysfs node), so it is
# applied for this machine only rather than for every platform that happens to
# use the v10 backend.
SRC_URI:append:minnow = " file://0010-v10-Support-ambient-LPM-mode-via-panel-self-refresh.patch;striplevel=2"

# The command-mode panel self-paces frame commits through its TE signal, so the
# extra pre-set() vsync wait in swap() was pure latency (~34 -> ~44 fps).
SRC_URI:append:minnow = " file://0011-v10-Drop-the-redundant-pre-set-vsync-wait-on-minnow.patch;striplevel=2"

# With swap() TE-self-pacing (0011), holding update delivery to the vsync
# callback is pure input latency (~15ms). Deliver immediately instead.
SRC_URI:append:minnow = " file://0012-v10-Deliver-update-requests-immediately-on-minnow.patch;striplevel=2"

# minnow's HWC_VSYNC_PERIOD query always fails and the generic 60 Hz fallback
# gave Qt (and, via wl_output, every client) a 16.7ms animation timestep while
# the panel really paces frames at 22.2ms (45 Hz) - fling animations ran slow
# and hitched on drift correction. Report the real period.
SRC_URI:append:minnow = " file://0013-v10-Report-minnow-s-real-45Hz-vsync-period.patch;striplevel=2"
