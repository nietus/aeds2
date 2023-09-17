#include <stdio.h>
//using a.exe < pub.in > t.out
int main() {
    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), stdin) != NULL) {
        printf("Read: %s", buffer);
    }
    return 0;
}

/*
2 lines
#include <stdio.h>

int main() {
    char buffer1[1024];
    char buffer2[1024];

    while (fgets(buffer1, sizeof(buffer1), stdin) != NULL &&
           fgets(buffer2, sizeof(buffer2), stdin) != NULL) {
        printf("Read: %s", buffer1);
        printf("Read: %s", buffer2);
    }
    
    return 0;
}
*/