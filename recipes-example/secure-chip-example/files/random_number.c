#include <stdio.h>
#include <cryptoauthlib.h>

#define REACH_SERIAL_APPEND     "8243"      /* 2 ASCII symbols */

ATCAIfaceCfg g_iface_config = {
    .iface_type        = ATCA_I2C_IFACE,
    .devtype           = ATECC508A,
    .atcai2c           = {
        .slave_address = 0xC0,
        .bus           = 0,
        .baud          = 400000,
    },
    .wake_delay        = 1500,
    .rx_retries        = 20
};

int read_atecc_random_number(uint8_t* random_number)
{
    ATCA_STATUS status = atcab_init(&g_iface_config);

    if (status != ATCA_SUCCESS) {
        printf("atcab_init() failed with ret=0x%08d\r\n", status);
        return -1;
    }

    status = atcab_random(random_number);;
    atcab_release();

    if (status != ATCA_SUCCESS) {
        printf("atcab_random() failed with ret=0x%08d\r\n", status);
        return -1;
    }

    return 0;
}

int main(void)
{
    uint8_t random_number[32];

    if (read_atecc_random_number(&random_number) != 0)
        return -1;

    for (int i = 0; i < 4; i++){
        for(int j = 0; j < 8; j++){
             printf("%02x ", random_number[i * 8 + j]);
        }
        printf("\n");
    }
    return 0;
}
