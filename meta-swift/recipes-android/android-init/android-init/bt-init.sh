#!/system/bin/sh
# WCNSS Bluetooth bringup for BlueZ, mirroring the stock init.qcom.bt.sh
# smd flow: run the Qualcomm SoC NVM initialization (BD address from
# /factory/bt_nv.bin, NVM tags, LE power class) over raw SMD, then let
# hci_smd register hci0.

log() {
    /system/bin/log -t bt-init "$*"
    echo "bt-init: $*" > /dev/kmsg
}

# Trigger WCNSS boot (PIL) in case nothing else has yet
echo 1 > /dev/wcnss_wlan 2>/dev/null

/system/bin/btnvtool -O
log "btnvtool -O exit code: $?"

# hci_qcomm_init itself retries opening /dev/smd3 for ~16s; retry the
# whole init in case WCNSS is not up yet that early in boot.
tries=0
until /system/bin/hci_qcomm_init -e -P 1; do
    tries=$((tries+1))
    if [ $tries -ge 5 ]; then
        log "hci_qcomm_init keeps failing, giving up"
        exit 1
    fi
    log "hci_qcomm_init failed, retrying ($tries)"
    sleep 2
done
log "Bluetooth QSoC NVM init done"

echo 1 > /sys/module/hci_smd/parameters/hcismd_set
log "hci_smd registration requested"
