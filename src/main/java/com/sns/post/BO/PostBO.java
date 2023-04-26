package com.sns.post.BO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.Dao.CommnetMapper;
import com.sns.common.FileManagerService;
import com.sns.like.Dao.LikeMapper;
import com.sns.post.Dao.PostMapper;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private CommnetMapper commentMapper;
	
	@Autowired
	private LikeMapper likeMapper;
	
	public int addPost(String loginId, int userId, String writeTextArea, MultipartFile file) {
		
		// 서버에 이미지 업로드후 imagePath받아옴
		String imagePath = fileManager.saveFile(loginId, file);
		
		return postMapper.insertPost(userId, writeTextArea, imagePath);
		
	}
	public List<Post> getPost() {
		return postMapper.selectPost();
	}
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postMapper.selectPostByPostIdUserId(postId, userId);
	}
	
	public int deletePost(int postId, int userId) {
		// 현재포스트 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		// 현재 포스트에서 이미지 경로 꺼내오기
		if (post == null) {
			logger.error("[포스트삭제] post is null. postId:{}, userId:{}", postId, userId);
		}
		
		// 포스트 삭제
		int resultCount = postMapper.deletePost(postId, userId);
		
		// 코멘트 삭제
		commentMapper.deleteCommentByPostId(postId);
		// 좋아요 삭제
		likeMapper.deletePostByPostId(postId);
		
		// 사진삭제
		fileManager.deleteFile(post.getImagePath());
		
		return resultCount;
	}
	
	

}
