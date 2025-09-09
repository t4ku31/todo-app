FROM node:20

WORKDIR /app

# 依存関係を先にコピーして install
COPY package*.json ./
RUN npm install

# ソースをコピー
COPY . .

EXPOSE 5173
CMD ["npm", "run", "dev", "--", "--host"]
