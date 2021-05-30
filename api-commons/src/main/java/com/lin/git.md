## git常用命令：
---
本地操作：
1. 初始化：`git init`
2. 设置签名：`git config user.name xxx` `git config user.email xxxx`
3. 查看状态：`git status`
4. 查看历史记录操作： `git log --oneline`
5. 版本切换：`git reset --hard 【版本号】`
---
分支操作：
1. 创建分支：`git branch 【分支名】`
2. 查看分支：`git branch -v`
3. 切换分支：`git checkout 【分支名】`
4. 合并分支：`git merge 【待合并的分支名】`
---
远程操作：
1. 设置地址别名：`git remote add 【别名】【远程地址】`
2. 推送到远程：`git push 【别名】 【分支名】`
3. 克隆：`git clone 【远程地址】`
4. 拉取到本地：`git pull`
5. 啦啦啦
