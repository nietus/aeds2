#include <stdio.h>

int main() {
    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), stdin) != NULL) {
        printf("Read: %s", buffer);
    }
    return 0;
}