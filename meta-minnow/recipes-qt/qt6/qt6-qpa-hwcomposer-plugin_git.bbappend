FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# minnow-only: these rely on the panel's 'interactivemode' node and TE self-pacing.
SRC_URI:append:minnow = " \
    file://0010-v10-Support-ambient-mode-via-panel-self-refresh.patch;striplevel=2 \
"
