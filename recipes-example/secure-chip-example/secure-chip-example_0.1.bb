SUMMARY = "Random number generator"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

S = "${WORKDIR}"
SRC_URI = "file://random_number.c"                                            

INSANE_SKIP_${PN} = "file-rdeps"

DEPENDS += "openssl-atecc508a"                                                       
RDEPENDS_${PN} += "openssl-atecc508a"

INC_DIRS = "-I${STAGING_INCDIR}/ateccssl"
LIB_DIRS = "-L${STAGING_LIBDIR}"
LIBS = "-lateccssl"

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} ${LIB_DIRS} ${INC_DIRS} ${S}/random_number.c -o random_number ${LIBS}
}

do_install() {
	install -d ${D}${bindir}
	install -c -m 0755 ${B}/random_number ${D}${bindir}
}
