#!/system/bin/sh
# WCNSS Bluetooth bringup for BlueZ, mirroring the stock init.qcom.bt.sh
# smd flow: boot the WCNSS core, run the Qualcomm SoC NVM initialization
# (BD address, NVM tags, LE power class) over raw SMD, then let hci_smd
# register hci0.

log() {
    /system/bin/log -t bt-init "$*"
    echo "bt-init: $*" > /dev/kmsg
}

# Trigger WCNSS boot (PIL) in case nothing else has yet
echo 1 > /dev/wcnss_wlan 2>/dev/null

n=0
until [ "$(cat /sys/devices/platform/wcnss_wlan.0/smd_channel_ready 2>/dev/null)" = "1" ]; do
    n=$((n+1))
    if [ $n -gt 60 ]; then
        log "timed out waiting for WCNSS SMD channels, continuing anyway"
        break
    fi
    sleep 1
done

/system/bin/btnvtool -O
log "btnvtool -O exit code: $?"

/system/bin/hci_qcomm_init -e -P 1
rc=$?
if [ $rc -ne 0 ]; then
    log "hci_qcomm_init failed ($rc), not enabling hci_smd"
    exit $rc
fi
log "Bluetooth QSoC NVM init done"

echo 1 > /sys/module/hci_smd/parameters/hcismd_set
log "hci_smd registration requested"
