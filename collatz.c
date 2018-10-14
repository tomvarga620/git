#include <stdio.h>

int main(int argc, char const *argv[]) {
    int num = 1;
    int i, n, max;
    int count = 0; //Use only for task 4.1, 4.2

    scanf("%d", &num);

    if (num>=1 && num<=1000)
    {
        while (num != 1) {  //main cycle

            if ( num%2==0)
            {
                num=num/2;
                printf("%d ", num);
                    count++;
            }

            else {
                num = num * 3 + 1;
                printf("%d ", num);
                    count++;
            }
        }


        printf("%d", num);
        printf("\nPrint count is: %d", count);
    }

    // uloha 4.2


//    for (i=100; i<=200; i++)
//    {
//        printf("%d ", i);
//        n=i;
//        max=i;
//        while (n != 1) {  //main cycle
//
//            if (n % 2 == 0)
//            {
//                n = n / 2;
//                count++;
//                if (n>max)
//                    max=n;
//            }
//            else
//                {
//                n = n * 3 + 1;
//                count++;
//                if (n>max)
//                    max=n;
//                }
//        }
//        printf("%d ", count);
//        printf("%d \n", max);
//        count=0;
//    }

    return 0;
}
