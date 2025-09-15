import { Sidebar, SidebarContent, SidebarHeader, SidebarMenu, SidebarMenuItem, SidebarMenuButton } from "@/components/ui/sidebar"
import { Home, CheckSquare, Settings } from "lucide-react"
import { Link } from "react-router-dom"
import Logo from "@/components/common/Logo"
import { cn } from "@/lib/utils";

type AppSidebarProps = React.HTMLAttributes<HTMLDivElement>;
export function AppSidebar({ className, ...props }: AppSidebarProps) {
    return (
        <Sidebar className={cn("w-80 bg-sidebar text-sidebar-foreground", className)}  {...props}>
            <SidebarHeader>
                <div className="flex items-center gap-2 px-2 py-2">
                    <Logo />
                </div>
            </SidebarHeader>
            <SidebarContent>
                <SidebarMenu>
                    <SidebarMenuItem>
                        <SidebarMenuButton asChild>
                            <Link to="/todo">
                                <Home className="h-4 w-4" />
                                <span>Dashboard</span>
                            </Link>
                        </SidebarMenuButton>
                    </SidebarMenuItem>
                    <SidebarMenuItem>
                        <SidebarMenuButton asChild>
                            <Link to="/todo/tasks">
                                <CheckSquare className="h-4 w-4" />
                                <span>Tasks</span>
                            </Link>
                        </SidebarMenuButton>
                    </SidebarMenuItem>
                    <SidebarMenuItem>
                        <SidebarMenuButton asChild>
                            <Link to="/todo/settings">
                                <Settings className="h-4 w-4" />
                                <span>Settings</span>
                            </Link>
                        </SidebarMenuButton>
                    </SidebarMenuItem>
                </SidebarMenu>
            </SidebarContent>
        </Sidebar >
    )
}
