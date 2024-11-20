// 评论区组件

import {
    Avatar,
    Box,
    Button,
    Card,
    Divider,
    Grid2,
    IconButton,
    TextField,
    Typography,
} from "@mui/material";
import { useEffect, useState } from "react";
import { api } from "@/utils/axios.ts";
import { Comment } from "@/models/comment.ts";
import useAuthStore from "@/stores/auth.ts";
import { Delete } from "@mui/icons-material";

function MyComment({
    comment,
    firstName,
    created_at,
    id,
}: {
    comment?: string;
    firstName?: string;
    created_at?: number;
    id?: number;
}) {
    const date = new Date(Number(created_at) * 1000);
    const authStore = useAuthStore();
    // 附加题删除评论
    // 前端调用接口
    function toDelete() {
        api()
            .delete(`comments/${id}`)
            .then((r) => {
                console.log(r);
            });
    }

    return (
        <>
            <Grid2 container sx={{ backgroundColor: "#f0f0f02d" }}>
                <Grid2 display="flex" sx={{ margin: 2 }}>
                    <Avatar sx={{ marginRight: 4, alignContent: "center" }}>
                        {firstName?.slice(0, 4)}
                    </Avatar>
                    <Box>
                        {comment ? (
                            <Typography variant="body1">{comment}</Typography>
                        ) : (
                            <Typography variant="body2" fontStyle="italic">
                                ...
                            </Typography>
                        )}
                        <Typography variant="caption">
                            {date.toLocaleString()}
                        </Typography>
                    </Box>
                    <Box sx={{ ml: 2 }}>
                        <IconButton
                            disabled={authStore.user?.role !== "admin"}
                            color="primary"
                            onClick={toDelete}
                            sx={{
                                ml: 2,
                                "&:hover": {
                                    bgcolor: "primary.light",
                                    color: "primary.contrastText",
                                },
                            }}
                        >
                            <Delete />
                        </IconButton>
                    </Box>
                </Grid2>
            </Grid2>
        </>
    );
}

export default function Comments({ id }: { id: number }) {
    const [comments, setComments] = useState<Array<Comment>>();
    const [commentSend, setCommentSend] = useState<string>();
    const authStore = useAuthStore();

    function fetchComments() {
        api()
            .get(`/articles/${id}/comments`)
            .then((res) => {
                const r = res.data;
                setComments(r.data);
            });
    }

    useEffect(() => {
        fetchComments();
    }, [comments]);

    function submitComment() {
        api()
            .post("/comments", {
                article_id: id,
                content: commentSend,
                user_id: authStore?.user?.id,
            })
            .then(() => {
                fetchComments();
                setCommentSend("");
            });
    }

    return (
        <>
            <Divider>评论区</Divider>
            <Card sx={{ marginTop: 3 }}>
                <Box
                    sx={{
                        display: "flex",
                        justifyContent: "space-between",
                        margin: "1rem 0",
                        gap: "1rem",
                    }}
                >
                    <TextField
                        maxRows="20"
                        minRows="2"
                        multiline
                        fullWidth
                        value={commentSend}
                        onChange={(e) => setCommentSend(e.target.value)}
                    />
                    <Button variant="contained" onClick={submitComment}>
                        评论
                    </Button>
                </Box>
                <Grid2>
                    {comments?.map((oneOfComments, index) => {
                        return (
                            <MyComment
                                key={index}
                                comment={oneOfComments.content}
                                firstName={oneOfComments.user?.username}
                                created_at={oneOfComments.created_at}
                                id={oneOfComments.id}
                            />
                        );
                    })}
                </Grid2>
            </Card>
        </>
    );
}
