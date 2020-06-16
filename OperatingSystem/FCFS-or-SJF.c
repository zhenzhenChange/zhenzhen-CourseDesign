#include <stdio.h>
#include <stdlib.h>

#define value 100

typedef struct process {
    char PID;           // 进程ID
    float arrivalTime;  // 到达时间
    float serviceTime;  // 服务时间
    float startTime;    // 开始时间
    float finishTime;   // 完成时间
    float aroundTime;   // 周转时间
    float weightAroundTime; // 带权周转时间
    struct process *link;   // 结构体指针
} Algorithm;

Algorithm *p, *q, *head = NULL;

struct process array[value];

/*
 * 按到达时间将 进程 进行排序
 *
 * processArray[] 存放排序后的进程
 * processNumber 进程数量
 * type :
 *      0 : FCFS
 *      1 : SJF
 *
 * */
struct process *sortArrivalTime(struct process processArray[], int processNumber, int type) {
    struct process temp;
    for (int i = 0; i < processNumber; i++) {
        for (int j = i + 1; j < processNumber; j++) {
            if (processArray[i].arrivalTime > processArray[j].arrivalTime) {
                temp = processArray[i];
                processArray[i] = processArray[j];
                processArray[j] = temp;
            }
        }
    }

    // 进入SJF排序阶段
    if (type == 1) {
        for (int m = 0; m < processNumber - 1; m++) {
            if (m == 0)
                processArray[m].finishTime = processArray[m].arrivalTime + processArray[m].serviceTime;
            else {
                if (processArray[m - 1].finishTime >= processArray[m].arrivalTime) {
                    processArray[m].startTime = processArray[m - 1].finishTime;
                } else {
                    processArray[m].startTime = processArray[m].arrivalTime;
                }
                processArray[m].finishTime = processArray[m].startTime + processArray[m].serviceTime;
            }

            int count = 0;
            for (int n = m + 1; n <= processNumber - 1; n++) {
                if (processArray[n].arrivalTime <= processArray[m].finishTime)
                    count++;
            }

            //按服务时间排序
            float min = processArray[m + 1].serviceTime;
            int next = m + 1;
            for (int k = m + 1; k < m + count; k++) {
                if (processArray[k + 1].serviceTime < min) {
                    min = processArray[k + 1].serviceTime;
                    next = k + 1;
                }

            }
            temp = processArray[m + 1];
            processArray[m + 1] = processArray[next];
            processArray[next] = temp;
        }
    }
    return processArray;
}

// 打印日志
void publicPrint(struct process processArray[], int processNumber, int type) {
    int i;
    if (type == 0) {
        printf("进程ID 到达时间 服务时间 开始时间 完成时间 周转时间 带权周转时间\n");
        for (i = 0; i < processNumber; i++) {
            printf("  %c     %.3f   %.3f   %.3f   %.3f    %.3f    %.3f \n",
                   processArray[i].PID,
                   processArray[i].arrivalTime,
                   processArray[i].serviceTime,
                   processArray[i].startTime,
                   processArray[i].finishTime,
                   processArray[i].aroundTime,
                   processArray[i].weightAroundTime);
        }
    } else if (type == 1) {
        printf("进程ID 服务时间 到达时间 开始时间 完成时间 周转时间 带权周转时间\n");
        for (i = 0; i < processNumber; i++) {
            printf("  %c     %.3f   %.3f   %.3f   %.3f    %.3f    %.3f \n",
                   processArray[i].PID,
                   processArray[i].serviceTime,
                   processArray[i].arrivalTime,
                   processArray[i].startTime,
                   processArray[i].finishTime,
                   processArray[i].aroundTime,
                   processArray[i].weightAroundTime);
        }
    }
}

// 各个时间的相关计算
void publicAlgorithm(struct process processArray[], int processNumber, int type) {
    int i;

    // 接收变量
    float receiveTime;

    // 开始时间 = 上一个进程的完成时间（第一个进程为到达时间） = 上一个进程的到达时间 + 上一个进程的服务时间
    // 完成时间 = 开始时间 + 服务时间
    // 周转时间 = 完成时间 - 到达时间
    // 带权周转时间 = 周转时间 / 服务时间

    for (i = 0; i < processNumber; i++) {
        if (i == 0) {
            processArray[i].startTime = processArray[i].arrivalTime;
        }
        if (i != 0) {
            receiveTime = processArray[i - 1].startTime + processArray[i - 1].serviceTime;
            if (processArray[i].arrivalTime > receiveTime) {
                processArray[i].startTime = processArray[i].arrivalTime;
            } else {
                processArray[i].startTime = receiveTime;
            }
        }
        processArray[i].finishTime = processArray[i].startTime + processArray[i].serviceTime;
        processArray[i].aroundTime = processArray[i].finishTime - processArray[i].arrivalTime;
        processArray[i].weightAroundTime = processArray[i].aroundTime / processArray[i].serviceTime;
    }
    printf("------------------------------------\n");
    printf("publicAlgorithm：\n");
    publicPrint(processArray, processNumber, type);
}

// 选择
void checkAlgorithm(struct process processArray[], int processNumber) {
    int type;
    printf("\n");
    printf("----------------------\n");
    printf("〔    0 ---- FCFS   〕\n");
    printf("〔    1 ---- SJF    〕\n");
    printf("----------------------\n");
    printf("请选择调度算法（其余指令 >>> 退出系统）：");
    scanf("%d", &type);
    switch (type) {
        case 0:
            publicAlgorithm(sortArrivalTime(processArray, processNumber, 0), processNumber, 0);
            checkAlgorithm(processArray, processNumber);
            break;
        case 1:
            publicAlgorithm(sortArrivalTime(processArray, processNumber, 1), processNumber, 1);
            checkAlgorithm(processArray, processNumber);
            break;
        default:
            break;
    }
}

// 主函数
int main() {
    int processNumber;
    int i;
    printf("请输入进程数（processNumber）：");
    scanf("%d", &processNumber);
    for (i = 0; i < processNumber; i++) {
        printf("------------------------------------\n");
        printf("第%d个进程的ID（PID）:", i + 1);
        scanf("%s", &array[i].PID);
        printf("第%d个进程到达时间（arriveTime）:", i + 1);
        scanf("%f", &array[i].arrivalTime);
        printf("第%d个进程服务时间（serviceTime）:", i + 1);
        scanf("%f", &array[i].serviceTime);
    }
    checkAlgorithm(array, processNumber);
    return 0;
}