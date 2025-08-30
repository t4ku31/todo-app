import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { PasswordInput } from '@/components/ui/password-input'
import { Card, CardContent } from "@/components/ui/card"
import { useNavigate } from 'react-router-dom'
import { Logo } from '@/components/common/Logo'
import { useSignupValidation } from '@/services/hooks/validation'

export default function SignupPage() {
    const navigate = useNavigate()

    const { register, handleSubmit, errors } = useSignupValidation({
        email: '',
        password: '',
        confirmPassword: '',
    });

    return (
        <form onSubmit={handleSubmit((data) => {
            console.log(data)
        })}>
        <div className="min-h-screen w-full flex items-center justify-center">
            <div>
                <Logo className="min-w-[300px] pb-3" />
                <Card className="rounded-lg h-[280px] min-w-[380px] ">
                    <CardContent className="">
                        <div className="flex flex-col pt-3 gap-1">
                            <div className="flex flex-col h-15 gap-1">
                                <Input type="email" placeholder="Email" {...register.email} />
                                <span className="text-red-500 text-sm ">
                                    {errors.email?.message}
                                </span>
                            </div>
                            <div className="flex flex-col h-15 gap-1">
                                <PasswordInput placeholder="Password" {...register.password} />
                                <span className="text-red-500 text-sm">
                                    {errors.password?.message }
                                </span>
                            </div>
                        </div>
                        <div className="flex flex-col gap-3 pt-5 b-5">
                            <Button variant="sign" className="w-full">Sign Up</Button>
                            <div className="flex justify-row justify-center items-center">
                                <p className="text-gray-500">Existing user?</p>
                                <Button
                                    variant="link"
                                    className="justify-center"
                                    onClick={() => navigate('/signin')}
                                >
                                    Signin
                                </Button>
                            </div>
                        </div>
                    </CardContent>
                </Card>
            </div>
        </div>
        </form>
    )
}