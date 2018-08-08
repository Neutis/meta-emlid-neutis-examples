SUMMARY = "MJPG-streamer takes JPGs from Linux-UVC compatible webcams, filesystem or other input plugins and streams them as M-JPEG via HTTP to webbrowsers, VLC and other software. It is the successor of uvc-streamer, a Linux-UVC streaming application with Pan/Tilt"
DEPENDS = "libgphoto2 v4l-utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "f387bb44e6c087271b763b27da998bf2e06c4f5d"
SRC_URI = "git://github.com/jacksonliam/mjpg-streamer.git;protocol=git"

S = "${WORKDIR}/git/mjpg-streamer-experimental"

inherit cmake

OECMAKE_GENERATOR="Unix Makefiles"
EXTRA_OECMAKE = "-DENABLE_HTTP_MANAGEMENT=ON"

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "${libdir}/*.so"

