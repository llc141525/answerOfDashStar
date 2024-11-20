// 主界面.显示所有文章的列表, 可以跳转到 ShowArticlePage.tsx 以显示对应的文章内容. 也可以跳转到登陆或者注册界面.

import React, { useEffect, useState } from "react";
import {
    Box,
    Button,
    Card,
    CardContent,
    Chip,
    Container,
    Divider,
    IconButton,
    List,
    ListItem,
    ListItemText,
    Pagination,
    PaginationItem,
    Paper,
    Stack,
    Typography,
} from "@mui/material";
import { Article } from "@/models/article.ts";
import { Add, Create } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import useAuthStore from "@/stores/auth.ts";
import { api } from "@/utils/axios.ts";
import useSiteStore from "@/stores/site.ts";

export default function HomePage() {
    const authStore = useAuthStore();
    const siteStore = useSiteStore();
    const navigator = useNavigate();
    const [users, setUsers] = useState<Array<string>>();
    const [totalPage, setTotalPage] = useState<number>(1);
    const [articles, setArticles] = useState<Array<Article>>();
    const [curPage, setCurPage] = useState<number>(1);
    useEffect(() => {
        api()
            .get(`/articles/page/${curPage}`)
            .then((res) => {
                const r = res.data;
                setArticles(r.data?.reverse());
                setTotalPage(r.totalPage);
                let userName = r.data.map((item: any) => item.author.username);
                userName = userName.reverse();
                setUsers(userName);
            });
    }, [curPage]);

    function handlePageChange(_e: React.ChangeEvent<unknown>, value: number) {
        setCurPage(value);
    }

    return (
        <Container maxWidth="md" sx={{ py: 4 }}>
            {/* 页面标题和新建按钮 */}
            <Stack
                direction="row"
                justifyContent="space-between"
                alignItems="center"
                sx={{ mb: 4 }}
            >
                <Typography
                    variant="h4"
                    align="center"
                    gutterBottom
                    sx={{
                        fontWeight: 600,
                        mb: 4,
                        color: "primary.main",
                    }}
                >
                    所有文章
                </Typography>

                {authStore?.user?.role === "admin" && (
                    <Button
                        variant="contained"
                        startIcon={<Add />}
                        onClick={() => {
                            navigator("/articles/new");
                            siteStore.setCurrentTitle("新建文章");
                        }}
                        sx={{
                            borderRadius: 2,
                            px: 3,
                            py: 1,
                        }}
                    >
                        新建文章
                    </Button>
                )}
            </Stack>

            {/* 文章列表 */}
            <Paper elevation={2} sx={{ borderRadius: 2 }}>
                {!articles?.length ? (
                    <Box sx={{ p: 4, textAlign: "center" }}>
                        <Typography color="text.secondary" sx={{ mb: 2 }}>
                            还没有写过文章
                        </Typography>
                        <Button
                            variant="outlined"
                            startIcon={<Add />}
                            onClick={() => {
                                navigator("/articles/new");
                                siteStore.setCurrentTitle("新建文章");
                            }}
                        >
                            创建第一篇文章
                        </Button>
                    </Box>
                ) : (
                    <List sx={{ p: 0 }}>
                        {articles?.map((e: Article, index: number) => (
                            <Box key={e.id}>
                                {index > 0 && <Divider />}
                                <ListItem
                                    sx={{
                                        "&:hover": {
                                            bgcolor: "action.hover",
                                        },
                                    }}
                                >
                                    <Card
                                        elevation={0}
                                        sx={{
                                            width: "100%",
                                            bgcolor: "transparent",
                                        }}
                                    >
                                        <CardContent sx={{ p: 2 }}>
                                            <Stack
                                                direction="row"
                                                justifyContent="space-between"
                                                alignItems="center"
                                            >
                                                <Button
                                                    fullWidth
                                                    sx={{
                                                        textAlign: "left",
                                                        textTransform: "none",
                                                    }}
                                                    onClick={() => {
                                                        navigator(
                                                            `/articles/${e.id}`
                                                        );
                                                        siteStore.setCurrentTitle(
                                                            e.title || ""
                                                        );
                                                    }}
                                                >
                                                    <ListItemText
                                                        primary={e.title}
                                                        primaryTypographyProps={{
                                                            variant: "h6",
                                                            color: "text.primary",
                                                        }}
                                                        secondary={new Date(
                                                            Number(
                                                                e.created_at
                                                            ) * 1000
                                                        ).toLocaleString()}
                                                    />
                                                    <ListItemText
                                                        secondary={
                                                            users
                                                                ? "Write BY: " +
                                                                  users[index]
                                                                : ""
                                                        }
                                                    />
                                                    <Chip
                                                        label={"label"}
                                                        color={"error"}
                                                    />
                                                </Button>

                                                {authStore?.user?.role ===
                                                    "admin" && (
                                                    <IconButton
                                                        color="primary"
                                                        onClick={() => {
                                                            navigator(
                                                                `/articles/${e.id}/edit`
                                                            );
                                                            siteStore.setCurrentTitle(
                                                                "编辑"
                                                            );
                                                        }}
                                                        sx={{
                                                            ml: 2,
                                                            "&:hover": {
                                                                bgcolor:
                                                                    "primary.light",
                                                                color: "primary.contrastText",
                                                            },
                                                        }}
                                                    >
                                                        <Create />
                                                    </IconButton>
                                                )}
                                            </Stack>
                                        </CardContent>
                                    </Card>
                                </ListItem>
                            </Box>
                        ))}
                    </List>
                )}
                {totalPage > 0 ? (
                    <Box sx={{ display: "flex", justifyContent: "center" }}>
                        <Pagination
                            count={totalPage}
                            onChange={handlePageChange}
                            page={curPage}
                            color="primary"
                            size="large"
                            sx={{ my: 2 }}
                            renderItem={(item) => (
                                <PaginationItem
                                    {...item}
                                    sx={{
                                        ml: 2,
                                        "&:hover": {
                                            bgcolor: "primary.light",
                                            color: "primary.contrastText",
                                        },
                                    }}
                                />
                            )}
                        ></Pagination>
                    </Box>
                ) : (
                    <></>
                )}
            </Paper>
        </Container>
    );
}
