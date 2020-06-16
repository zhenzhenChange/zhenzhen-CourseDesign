#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <string.h>

#define MEMORY_BLOCKS 20    // 内存物理总块数
#define PROGRAM_PAGES 50    // 程序分页数上限
#define PAGE_USE_LENGTH 100 // 页面走向长度最大值
#define FIFO 1              // 先进先出置换
#define LRU 2               // 最久未用置换

int memoryBlocks = 0;       // 分配内存物理块数
int programPages = 0;       // 程序分页数

int memoryAllocation[MEMORY_BLOCKS];    // 内存分配情况

/*
 * 访问字段
 * */
struct Visit {
    int loadTime;   // 装载时间
    int frequency;  // 使用频率的顺序，0为最高
};

/*
 * 页表
 * */
struct pageTable {
    int pageID;         // 页号
    int blockID;        // 物理块号
    bool state;         // 状态位
    bool modify;        // 修改位
    struct Visit visit; // 访问字段
} Page[PROGRAM_PAGES];

int createRandom(int i, int j);

void init(int m, int p);

void pageAdjust(int p);

bool aSingleVisit(int p, int f);

int replacePage(int f);

void newLine(int length);

void showData();

void simulation(int l, int f);

void menu();

/*
 * 生成随机数
 * */
int createRandom(int i, int j) {
    int randomNumber;
    randomNumber = rand() % (j - i + 1) + i;
    return randomNumber;
}

/*
 * 初始化
 * */
void init(int m, int p) {
    memoryBlocks = m;
    programPages = p;
    for (int i = 0; i < memoryBlocks; i++) {
        memoryAllocation[i] = -1;
    }
    for (int i = 0; i < programPages; i++) {
        // 初始化赋予默认值
        Page[i].pageID = i;
        Page[i].blockID = -1;
        Page[i].state = false;
        Page[i].modify = false;
        Page[i].visit.loadTime = 0;
        Page[i].visit.frequency = -1;
    }
    printf("\n初始化完成\n");
}

/*
 * 内存中其他页的调整
 * */
void pageAdjust(int p) {
    int i;
    for (i = 0; i < programPages; i++) {
        if (Page[i].state == true) {    // 内存中的页
            Page[i].visit.loadTime += 1;
            if (Page[p].visit.frequency == -1) {    // 该页不在内存
                Page[i].visit.frequency += 1;
            } else {    // 该页在内存
                if (Page[i].visit.frequency < Page[p].visit.frequency) {
                    Page[i].visit.frequency += 1;
                }
            }
        }
    }
}

/*
 * 单次访问
 * p为要访问的页
 * f为置换算法
 * */
bool aSingleVisit(int p, int f) {
    int i;
    bool flag = false;    // 访问成功的标记
    if (Page[p].state == false) {               // 该页未在内存中
        for (i = 0; i < memoryBlocks; i++) {
            if (memoryAllocation[i] == -1) {    // 有内存块可供分配
                flag = true;
                pageAdjust(p);    // 调整当前顺序
                memoryAllocation[i] = p;
                Page[p].blockID = i;
                Page[p].state = true;
                Page[p].visit.loadTime += 1;
                Page[p].visit.frequency = 0;
                break;
            }
        }
    } else {    // 该页已在内存中
        flag = true;
        pageAdjust(p);
        Page[p].visit.frequency = 0;
    }
    if (flag == false) {    // 需要置换
        replacePage(f);
    }
    return flag;
}

/*
 * 页面置换算法
 * */
int replacePage(int f) {
    int i;
    int swapOut = -1;   // 被换出的页号
    int free = -1;      // 空出的物理块号
    if (f == FIFO) {
        for (i = 0; i < memoryBlocks; i++) {
            if (Page[memoryAllocation[i]].visit.loadTime > swapOut) {
                swapOut = Page[memoryAllocation[i]].visit.loadTime;
                free = i;
            }
        }
        swapOut = memoryAllocation[free];
    } else if (f == LRU) {
        for (i = 0; i < memoryBlocks; i++) {
            if (Page[memoryAllocation[i]].visit.frequency == memoryBlocks - 1) {
                free = i;
                break;
            }
        }
        swapOut = memoryAllocation[free];
    }
    // 收尾操作
    memoryAllocation[free] = -1;
    Page[swapOut].blockID = -1;
    Page[swapOut].state = false;
    Page[swapOut].visit.loadTime = 0;
    Page[swapOut].visit.frequency = -1;
    return swapOut;
}

/*
 * 换行（页表显示用）
 * */
void newLine(int length) {
    int i;
    printf("\n+--------");
    for (i = 0; i < length; i++)
        printf("-----");
    printf("+\n");
}

/*
 * 页表显示
 * */
void showData() {
    if (memoryBlocks != 0 && programPages != 0) {
        printf("\n             内存表");
        newLine(memoryBlocks);
        printf("|  块号  |");
        for (int i = 0; i < memoryBlocks; i++) {
            printf("%3d |", i);
        }
        newLine(memoryBlocks);
        printf("|  页号  |");
        for (int i = 0; i < memoryBlocks; i++) {
            printf("%3d |", memoryAllocation[i]);
        }
        newLine(memoryBlocks);
        printf("\n             页表");
        newLine(programPages);
        printf("|  页号  |");
        for (int i = 0; i < programPages; i++) {
            printf("%3d |", i);
        }
        newLine(programPages);
        printf("|物理块号|");
        for (int i = 0; i < programPages; i++) {
            printf("%3d |", Page[i].blockID);
        }
        newLine(programPages);
        printf("| 状态位 |");
        for (int i = 0; i < programPages; i++) {
            if (Page[i].state == true)
                printf("  y |");
            else
                printf("  n |");
        }
        newLine(programPages);
        printf("| 修改位 |");
        for (int i = 0; i < programPages; i++) {
            if (Page[i].modify == true)
                printf("  y |");
            else
                printf("  n |");
        }
        newLine(programPages);
        printf("|装载时间|");
        for (int i = 0; i < programPages; i++) {
            printf("%3d |", Page[i].visit.loadTime);
        }
        newLine(programPages);
        printf("|频率顺序|");
        for (int i = 0; i < programPages; i++) {
            printf("%3d |", Page[i].visit.frequency);
        }
        newLine(programPages);
    } else {
        printf("\n尚未初始化，请先初始化数据\n");
    }
}

/*
 * 模拟操作
 * */
void simulation(int l, int f) {
    init(memoryBlocks, programPages);
    int lose = 0;
    int length[PAGE_USE_LENGTH];
    for (int i = 0; i < l; i++) {
        int a;
        a = createRandom(0, programPages - 1);
        length[i] = a;
        bool flag;
        flag = aSingleVisit(a, f);
        if (flag == false) {
            lose += 1;
            aSingleVisit(a, f);
        }
    }
    float m;
    float Lose = lose;
    float L = l;
    m = Lose / L;
    printf("\n随机访问序列为(页号) ");
    for (int i = 0; i < l; i++) {
        printf("%3d", length[i]);
    }
    printf("\n\n缺页率为%.2f%%\n", m * 100);
}

/*
 * 菜单
 * */
void menu() {
    printf("\n                             页面置换模拟\n");
    printf("\n初始化(init) 显示页表信息(show) 页面置换算法(FIFO)或(LRU) 清屏(clear)\n");
    char code[20];
    while (1) {
        printf("\n");
        scanf("%s", code);
        if (_stricmp(code, "init") == 0) {          // 初始化
            memoryBlocks = 0;
            programPages = 0;
            int a, b;
            printf("\n请输入进程的分页数(最大%d)  ", PROGRAM_PAGES);
            scanf("%d", &a);
            while (a > PROGRAM_PAGES || a < 1) {
                printf("\n输入数值非法,请重新输入", PROGRAM_PAGES);
                scanf("%d", &a);
            }
            printf("\n请输入为该进程分配的物理块数(最大%d)  ", MEMORY_BLOCKS);
            scanf("%d", &b);
            while (b > MEMORY_BLOCKS || b < 1) {
                printf("\n输入数值非法,请重新输入", MEMORY_BLOCKS);
                scanf("%d", &b);
            }
            init(b, a);
        } else if (_stricmp(code, "show") == 0) {   // 显示数据
            showData();
        } else if (_stricmp(code, "FIFO") == 0) {   // FIFO
            int l;
            printf("\n请输入页面走向长度  ");
            scanf("%d", &l);
            simulation(l, FIFO);
        } else if (_stricmp(code, "LRU") == 0) {    // LRU
            int l;
            printf("\n请输入页面走向长度  ");
            scanf("%d", &l);
            simulation(l, LRU);
        } else if (_stricmp(code, "clear") == 0) {  // 清屏
            system("cls");
            printf("\n                             页面置换模拟\n");
            printf("\n初始化(init) 显示页表信息(show) 页面置换算法(FIFO)或(LRU) 清屏(clear)\n");
        } else printf("命令无效，请重新输入\n");
    }
}

int main() {
    srand(time(NULL));
    menu();
    getchar();
    return 0;
}