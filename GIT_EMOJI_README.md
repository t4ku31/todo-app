# Git Emoji 使い方ガイド 🎉

このプロジェクトでは、git commitで絵文字（emoji）を使えるように設定されています。

## 🚀 セットアップ

1. **Git設定の適用**
   ```bash
   git config --local commit.template .gitmessage
   ```

2. **スクリプトの実行権限付与**
   ```bash
   chmod +x git-emoji.sh
   ```

## 📝 使い方

### 方法1: 通常のgit commit（テンプレート使用）
```bash
git commit
```
`.gitmessage`テンプレートが開き、絵文字の一覧が表示されます。

### 方法2: スクリプトを使用
```bash
./git-emoji.sh ✨ "Add new feature"
./git-emoji.sh 🐛 "Fix login bug"
./git-emoji.sh 📝 "Update documentation"
```

### 方法3: キーワードを使用
```bash
./git-emoji.sh feat "Add new feature"
./git-emoji.sh fix "Fix login bug"
./git-emoji.sh docs "Update documentation"
```

## 🎨 よく使う絵文字

| 絵文字 | キーワード | 用途 |
|--------|------------|------|
| 🎉 | `init` | 初期コミット |
| ✨ | `feat` | 新機能追加 |
| 🐛 | `fix` | バグ修正 |
| 📝 | `docs` | ドキュメント |
| 💄 | `style` | UI/スタイル |
| ♻️ | `refactor` | リファクタリング |
| ⚡️ | `perf` | パフォーマンス改善 |
| 🔧 | `chore` | 設定変更 |
| 🚀 | `deploy` | デプロイ |
| 🧪 | `test` | テスト |
| ➕ | `deps` | 依存関係追加 |
| ➖ | `remove` | 依存関係削除 |
| 💥 | `breaking` | 破壊的変更 |

## 🔧 カスタマイズ

### 新しい絵文字を追加
`git-emoji.sh`のcase文に新しい絵文字を追加できます：

```bash
"newtype"|"🆕")
    EMOJI="🆕"
    ;;
```

### テンプレートの変更
`.gitmessage`ファイルを編集して、絵文字の一覧をカスタマイズできます。

## 💡 便利なエイリアス

`.gitconfig`に便利なgitエイリアスが設定されています：

- `git st` = `git status`
- `git co` = `git checkout`
- `git br` = `git branch`
- `git ci` = `git commit`
- `git lg` = `git log --oneline --graph --decorate --all`

## 🎯 使用例

```bash
# 新機能追加
./git-emoji.sh ✨ "Add user authentication"

# バグ修正
./git-emoji.sh 🐛 "Fix password reset issue"

# ドキュメント更新
./git-emoji.sh 📝 "Update API documentation"

# リファクタリング
./git-emoji.sh ♻️ "Refactor user service"

# 依存関係追加
./git-emoji.sh ➕ "Add axios for HTTP requests"
```

## 🚨 注意事項

- 絵文字はコミット履歴の可読性を向上させます
- チーム内で統一した絵文字の使い方を決めることをお勧めします
- 絵文字だけでなく、分かりやすいコミットメッセージも重要です

## 🔗 参考リンク

- [Conventional Commits](https://www.conventionalcommits.org/)
- [Gitmoji](https://gitmoji.dev/)
- [Emoji Cheat Sheet](https://www.webfx.com/tools/emoji-cheat-sheet/)
