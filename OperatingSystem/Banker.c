#include<stdio.h>

#define processNumber 5     // 5个进程
#define resourceNumber 3    // 3类资源

int Available[resourceNumber];                  // 可利用资源向量
int Max[processNumber][resourceNumber];         // 最大需求矩阵
int Allocation[processNumber][resourceNumber];  // 分配矩阵
int Need[processNumber][resourceNumber];        // 需求矩阵

void InitializeData();

void ShowData(int line);

void CalcMaxMatrix();

int Equals(int a[resourceNumber], int b[resourceNumber]);

int CheckSafe();

int CheckFinish(int Finish[resourceNumber]);

void Add(int *a, int b[resourceNumber]);

void Minus(int *a, int b[resourceNumber]);

int Request(int P, int Request[resourceNumber]);

void RequestShowMsg(int P, int R[resourceNumber]);

int main() {
    InitializeData();
    printf("===================初始数据如下===================\n");
    ShowData(0);
    CheckSafe();

    //进程P1 申请资源{1,0,2}
    int apply[resourceNumber] = {1, 0, 2};
    RequestShowMsg(1, apply);

    //进程P4 申请资源{1,0,2}
    int apply2[resourceNumber] = {3, 3, 0};
    RequestShowMsg(4, apply2);

    //进程P0 申请资源{0,2,0}
    int apply3[resourceNumber] = {0, 2, 0};
    RequestShowMsg(0, apply3);

    return 0;
}

// 手动初始化数据,资源分配表
void InitializeData() {
    Allocation[0][0] = 0, Allocation[0][1] = 1, Allocation[0][2] = 0;
    Allocation[1][0] = 2, Allocation[1][1] = 0, Allocation[1][2] = 0;
    Allocation[2][0] = 3, Allocation[2][1] = 0, Allocation[2][2] = 2;
    Allocation[3][0] = 2, Allocation[3][1] = 1, Allocation[3][2] = 1;
    Allocation[4][0] = 0, Allocation[4][1] = 0, Allocation[4][2] = 2;

    Need[0][0] = 7, Need[0][1] = 4, Need[0][2] = 3;
    Need[1][0] = 1, Need[1][1] = 2, Need[1][2] = 2;
    Need[2][0] = 6, Need[2][1] = 0, Need[2][2] = 0;
    Need[3][0] = 0, Need[3][1] = 1, Need[3][2] = 1;
    Need[4][0] = 4, Need[4][1] = 3, Need[4][2] = 1;

    Available[0] = 3, Available[1] = 3, Available[2] = 2;

    CalcMaxMatrix();
}

// 进程资源请求 P:进程i,r申请资源数{1,1,1} 返回1成功 0失败
int Request(int P, int Request[resourceNumber]) {
    printf("进程P%d申请资源%d %d %d:\n", P, Request[0], Request[1], Request[2]);

    if (!Equals(Request, Need[P])) {
        printf("进程P%d,Request:%d %d %d > Need:%d %d %d 申请失败,所需资源数超过宣布最大值！\n",
               P, Request[0], Request[1], Request[2], Need[P][0], Need[P][1], Need[P][2]);
        return 0;
    }

    if (!Equals(Request, Available)) {
        printf("进程P%d,Request:%d %d %d > Available:%d %d %d 申请失败,尚无足够资源，该进程需要等待！\n",
               P, Request[0], Request[1], Request[2], Available[0], Available[1], Available[2]);
        return 0;
    }

    printf("进程P%d,Request:%d %d %d <= Need:%d %d %d\n",
           P, Request[0], Request[1], Request[2], Need[P][0], Need[P][1], Need[P][2]);
    printf("进程P%d,Request:%d %d %d <= Available:%d %d %d \n",
           P, Request[0], Request[1], Request[2], Available[0], Available[1], Available[2]);

    Minus(Available, Request);  // Available -= Request
    Add(Allocation[P], Request);// Allocation += Request
    Minus(Need[P], Request);    // Need -= Request

    int safeState = CheckSafe();
    if (safeState) {
        return safeState;       // 分配后处于安全状态 分配成功
    }

    //分配后处于不安全状态 分配失败，本次分配作废，回复原来的资源分配状态
    Add(Available, Request);        //Available += Request
    Minus(Allocation[P], Request);  //Allocation -= Request
    Add(Need[P], Request);          //Need += Request
    return safeState;
}

//带命令提示符提示的请求
void RequestShowMsg(int P, int R[resourceNumber]) {
    // 拟定模拟：进程P 申请资源Request{1,0,2}
    printf("\n模拟分配资源：P%d申请资源 %d %d %d\n=================================================\n", P, R[0], R[1], R[2]);
    int State = Request(P, R);
    if (State) {
        printf("本次资源分配成功！\n");
        ShowData(0);
    } else {
        printf("本次资源分配失败！进程P%d需要等待\n", P);
    }
}

//安全性检测，判断当前是否处于安全状态
int CheckSafe() {
    printf("开始安全性检查:\n");
    int Finish[processNumber] = {0};
    int Work[resourceNumber] = {0};
    Add(Work, Available);
    for (int i = 0; i < processNumber; i++) {
        if (Finish[i])continue;
        if (!Equals(Need[i], Work))continue;
        Add(Work, Allocation[i]);   //Work += Allocation;
        Finish[i] = 1;              //Finish[i]=True;
        printf("P%d进程,Work=%d %d %d,Finish=true,安全状态\n", i, Work[0], Work[1], Work[2]);
        i = -1;
    }
    if (CheckFinish(Finish)) {
        printf("安全状态检查完毕:【Finish全为true,系统处于安全状态】\n");
        return 1;
    }
    printf("安全状态检查完毕:【Finish存在False,系统处于不安全状态】\n");
    return 0;
}

// 检查标志所有都为True，是返回1 不是返回0
int CheckFinish(int Finish[resourceNumber]) {
    for (int i = 0; i < resourceNumber; i++) {
        if (Finish[i] == 0) return 0;
    }
    return 1;
}

// 打印数据
void ShowData(int line) {
    printf("PID	Max	Allocation	Need	Available\n");
    for (int i = 0; i < processNumber; i++) {
        printf("p%d:\t", i);
        for (int j = 0; j < resourceNumber; j++) {
            printf("%d ", Max[i][j]);
        }
        printf("\t  ");
        for (int j = 0; j < resourceNumber; j++) {
            printf("%d ", Allocation[i][j]);
        }
        printf("\t");
        for (int j = 0; j < resourceNumber; j++) {
            printf("%d ", Need[i][j]);
        }

        if (line == i) {
            printf("\t  ");
            for (int j = 0; j < resourceNumber; j++) {
                printf("%d ", Available[j]);
            }
        }

        printf("\n");
    }

}

// 计算Max
void CalcMaxMatrix() {
    for (int i = 0; i < processNumber; i++) {
        for (int j = 0; j < resourceNumber; j++) {
            Max[i][j] = Need[i][j] + Allocation[i][j];
        }
    }
}

void Add(int *a, int b[resourceNumber]) {
    for (int i = 0; i < resourceNumber; i++) {
        a[i] = a[i] + b[i];
    }
}

void Minus(int *a, int b[resourceNumber]) {
    for (int i = 0; i < resourceNumber; i++) {
        a[i] = a[i] - b[i];
    }
}

//资源比较   a<=b 返回1     a>b 返回0
int Equals(int a[resourceNumber], int b[resourceNumber]) {
    for (int i = 0; i < resourceNumber; i++) {
        if (a[i] > b[i]) return 0;
    }
    return 1;
}