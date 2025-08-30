import { useForm } from 'react-hook-form';

type SignupForm = {
    email: string;
    password: string;
    confirmPassword: string;
};

const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

export function useSignupValidation(defaultValues: SignupForm) {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<SignupForm>({ defaultValues });


    return {
        register: {
            email: register('email', {
                required: 'メールアドレスを入力してください',
                pattern: {
                    value: emailRegex,
                    message: '有効なメールアドレスを入力してください',
                },
            }),
            password: register('password', {
                required: 'パスワードを入力してください',
                minLength: {
                    value: 6,
                    message: 'パスワードは6文字以上で入力してください',
                },
            }),
        },
        errors,
        handleSubmit,
    };
}
