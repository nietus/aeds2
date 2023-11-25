#include <stdio.h>
#include <string.h>

int main() {
    int iterations;
    scanf("%d", &iterations);

    for (int i = 0; i < iterations; i++) {
        char f[200] = "";
        char um[100], dois[100];
        
        scanf(" %[^\n]", um);
        scanf(" %[^\n]", dois);

        int maxLength = (strlen(um) > strlen(dois)) ? strlen(um) : strlen(dois);

        for (int j = 0; j < maxLength; j += 2) {
            if (j < strlen(um)) {
                strncat(f, &um[j], 1);
            }
            if (j + 1 < strlen(um)) {
                strncat(f, &um[j + 1], 1);
            }
            if (j < strlen(dois)) {
                strncat(f, &dois[j], 1);
            }
            if (j + 1 < strlen(dois)) {
                strncat(f, &dois[j + 1], 1);
            }
        }
        printf("%s\n", f);
    }

    return 0;
}