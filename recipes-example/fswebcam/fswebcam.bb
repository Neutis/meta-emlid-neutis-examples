DESCRIPTION = "Webcam image grabber and manipulation application."
SECTION = "examples"
DEPENDS = "gd"
HOMEPAGE = "http://www.sanslogic.co.uk/fswebcam/"
LICENSE="GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=393a5ca445f6965873eca0259a17f833"

SRCREV="c417cd8588f93f3f6c4fc687c2cb8f0f9d70b9b0"
SRC_URI = "git://github.com/fsphil/fswebcam.git;protocol=git"

S = "${WORKDIR}/git"

inherit autotools

do_compile() {
    # Due to lack of autogen.sh and bad Makefile.in in the project, we can't
    # build it in separate directory. So move all src to build dir.
    cp -r ${S}/* ${B}/
    oe_runmake
}

