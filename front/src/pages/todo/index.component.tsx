import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { useState, useCallback } from "react";

interface Todo {
    id: number;
    text: string;
    completed: boolean;
}

export default function TodoPage() {
    const [todos, setTodos] = useState<Todo[]>([]);
    const [inputValue, setInputValue] = useState("");

    const addTodo = useCallback(() => {
        if (inputValue.trim()) {
            const newTodo: Todo = {
                id: Date.now(),
                text: inputValue.trim(),
                completed: false
            };
            setTodos(prev => [...prev, newTodo]);
            setInputValue("");
        }
    }, [inputValue]);

    const toggleTodo = useCallback((id: number) => {
        setTodos(prev =>
            prev.map(todo =>
                todo.id === id ? { ...todo, completed: !todo.completed } : todo
            )
        );
    }, []);

    const deleteTodo = useCallback((id: number) => {
        setTodos(prev => prev.filter(todo => todo.id !== id));
    }, []);

    return (
        <>
            <Card className="flex-[0.7] h-[70%] overflow-x-auto">
                <CardHeader>
                    <CardTitle>Todo List</CardTitle>
                </CardHeader>
                <CardContent>
                    <div className="flex gap-2 mb-4">
                        <Input
                            placeholder="Add a new todo..."
                            value={inputValue}
                            onChange={(e) => setInputValue(e.target.value)}
                            onKeyPress={(e) => e.key === 'Enter' && addTodo()}
                        />
                        <Button onClick={addTodo}>Add</Button>
                    </div>
                    <div className="space-y-2">
                        {todos.length === 0 ? (
                            <p className="text-gray-500 text-center py-4">No todos yet. Add one above!</p>
                        ) : (
                            todos.map(todo => (
                                <div key={todo.id} className="flex items-center gap-2 p-2 border rounded">
                                    <input
                                        type="checkbox"
                                        checked={todo.completed}
                                        onChange={() => toggleTodo(todo.id)}
                                    />
                                    <span className={`flex-1 ${todo.completed ? 'line-through text-gray-500' : ''}`}>
                                        {todo.text}
                                    </span>
                                    <Button
                                        variant="outline"
                                        size="sm"
                                        onClick={() => deleteTodo(todo.id)}
                                    >
                                        Delete
                                    </Button>
                                </div>
                            ))
                        )}
                    </div>
                </CardContent>
            </Card>
        </>
    )
}